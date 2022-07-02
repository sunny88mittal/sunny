package Zerodha.Trader.Strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.kiteconnect.utils.Constants;
import com.zerodhatech.models.Order;
import com.zerodhatech.models.Position;

import Zerodha.Trader.Core.AppConstants;
import Zerodha.Trader.Core.KiteHandler;
import Zerodha.Trader.Core.TradingSymbolHelper;
import Zerodha.Trader.Logging.Logger;

public class ShortStraddleWithStopLoss implements IStrategy {

	private int qty;

	private boolean isTradeOpen;

	private double lastTradedAt;

	private String optionDateValue;

	private KiteHandler kiteHandler;

	private int STOP_LOSS_PER = 25;

	private static String SSWSL_CHECK_POINT_FILE = "..\\SSWSLCheckPointFile.txt";

	public ShortStraddleWithStopLoss(int qty, String optionDateValue, KiteHandler orderHandler) {
		this.qty = qty;
		this.optionDateValue = optionDateValue;
		this.kiteHandler = orderHandler;
		this.lastTradedAt = 0.0;
		this.isTradeOpen = false;
	}

	public void initialize() {
		File file = new File(SSWSL_CHECK_POINT_FILE);
		if (file.exists()) {
			try {
				Scanner sc = new Scanner(file);
				if (sc.hasNextLine()) {
					// Read the banknifty price at which last trade happened
					String line = sc.nextLine();
					int lastTradedAt = Integer.parseInt(line);
					this.lastTradedAt = lastTradedAt;
					this.isTradeOpen = true;
					Logger.print(this.getClass(), "Trade already open");
				}
			} catch (Throwable e) {
				Logger.print(this.getClass(), "Error in initializing");
			}
		}
		Logger.print(this.getClass(), "Initialization Complete");
	}

	public void doNext(double price) {
		LocalDateTime now = LocalDateTime.now();
		if (now.getMinute() % 15 == 0 && now.getSecond() == 0) {
			Logger.print(this.getClass(), "Price is :" + price);
		}
		if (!isTradeOpen && now.getHour() == 9 && now.getMinute() >= 20) {
			List<Order> orders = tradeOptions(price, Constants.TRANSACTION_TYPE_SELL);
			placeBuyLimitOrder(orders);
			isTradeOpen = true;
			lastTradedAt = price;
			doCheckPointing((int) lastTradedAt);
		} else if (isTradeOpen && now.getHour() == 15 && now.getMinute() >= 29) {
			tradeOptions(lastTradedAt, Constants.TRANSACTION_TYPE_BUY);
			isTradeOpen = false;
			lastTradedAt = 0;
			clearCheckPoint();
		}
	}
	
	public void disconnectedFromBroker() {
		//Do nothing
	}

	private void doCheckPointing(int price) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(SSWSL_CHECK_POINT_FILE));
			String toWrite = price > 0 ? price + "" : "";
			writer.write(toWrite);
			writer.close();
		} catch (IOException e) {
			Logger.print(this.getClass(), "Error in adding check point");
		}
	}

	private void clearCheckPoint() {
		doCheckPointing(0);
	}

	private List<Order> tradeOptions(double price, String transactionType) {
		return placeNRMLOrders(getStrikeToTrade((int) price), transactionType);
	}

	private List<Order> placeNRMLOrders(int strike, String transactionType) {
		String putOptionSymbol = getPutOptionSymbol(strike);
		String callOptionSymbol = getCallOptionSymbol(strike);
		boolean isBuyOrder = Constants.TRANSACTION_TYPE_BUY.equals(transactionType);
		boolean isSellOrder = Constants.TRANSACTION_TYPE_SELL.equals(transactionType);
		List<Order> orders = new ArrayList<Order>();

		try {
			// Trade Put position
			if ((isBuyOrder && isPositionOpen(putOptionSymbol)) || isSellOrder) {
				Order order = kiteHandler.placeMarketOrder(qty, putOptionSymbol, Constants.EXCHANGE_NFO,
						transactionType);
				orders.add(order);
				Logger.print(this.getClass(), qty + ":" + putOptionSymbol + ":" + transactionType);
			} else {
				System.out.println("Position already closed for :" + putOptionSymbol);
			}

			// Close call position
			if ((isBuyOrder && isPositionOpen(callOptionSymbol)) || isSellOrder) {
				Order order = kiteHandler.placeMarketOrder(qty, callOptionSymbol, Constants.EXCHANGE_NFO,
						transactionType);
				orders.add(order);
				Logger.print(this.getClass(), qty + ":" + callOptionSymbol + ":" + transactionType);
			} else {
				Logger.print(this.getClass(), "Position already closed for :" + callOptionSymbol);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return orders;
	}

	private List<Order> placeBuyLimitOrder(List<Order> orders) {
		List<Order> placedOrders = new ArrayList<Order>();
		for (Order order : placedOrders) {
			String orderType = Constants.TRANSACTION_TYPE_BUY;
			String tradingSymbol = order.tradingSymbol;
			int quantity = Integer.parseInt(order.filledQuantity);
			int price = Integer.parseInt(order.averagePrice) * (1 + STOP_LOSS_PER / 100);
			try {
				Order placedOrder = kiteHandler.placeLimitOrder(quantity, tradingSymbol, Constants.EXCHANGE_NFO,
						orderType, price);
				placedOrders.add(placedOrder);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

		return placedOrders;
	}

	private boolean isPositionOpen(String tradingSymbol) throws IOException, KiteException {
		List<Position> positions = kiteHandler.getPositions().get("net");
		for (Position position : positions) {
			if (Math.abs(position.netQuantity) > 0 && position.tradingSymbol.equals(tradingSymbol)) {
				return true;
			}
		}
		return false;
	}

	private String getPutOptionSymbol(int strike) {
		return getOptionSymbol(strike, AppConstants.OPTION_TYPE_PE);
	}

	private String getCallOptionSymbol(int strike) {
		return getOptionSymbol(strike, AppConstants.OPTION_TYPE_CE);
	}

	private String getOptionSymbol(int strike, String type) {
		return TradingSymbolHelper.getFullOptionSymbol(AppConstants.INDEX_BANKNIFTY, optionDateValue, strike, type);
	}

	private int getStrikeToTrade(int price) {
		int strike = (int) (price - price % 100);
		strike += price % 100 > 50 ? 100 : 0;
		return strike;
	}
}
