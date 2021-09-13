package Zerodha.Trader.Strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.kiteconnect.utils.Constants;
import com.zerodhatech.models.Position;

import Zerodha.Trader.Core.AppConstants;
import Zerodha.Trader.Core.OrderHandler;
import Zerodha.Trader.Core.PositionsProvider;
import Zerodha.Trader.Core.TradingSymbolHelper;

public class ShortStraddleWithAdjustment implements IStrategy {

	private int qty;

	private boolean isTradeOpen;

	private double lastTradedAt;

	private String optionDateValue;

	private OrderHandler orderHandler;

	private PositionsProvider positionsProvider;

	public ShortStraddleWithAdjustment(int qty, String optionDateValue, OrderHandler orderHandler,
			PositionsProvider positionsProvider) {
		this.qty = qty;
		this.optionDateValue = optionDateValue;
		this.orderHandler = orderHandler;
		this.positionsProvider = positionsProvider;
		this.lastTradedAt = 0.0;
		this.isTradeOpen = false;
	}

	public void initialize() {
		File file = new File(AppConstants.SSWA_CHECK_POINT_FILE);
		if (file.exists()) {
			try {
				Scanner sc = new Scanner(file);
				if (sc.hasNextLine()) {
					// Read the banknifty price at which last trade happened
					String line = sc.nextLine();
					int lastTradedAt = Integer.parseInt(line);
					int strikeTradedAt = getStrikeToTrade(lastTradedAt);

					// Confirm if the position is still open
					String putOptionSymbol = getPutOptionSymbol(strikeTradedAt);
					String callOptionSymbol = getCallOptionSymbol(strikeTradedAt);
					boolean hasPosition = isPositionOpen(callOptionSymbol) && isPositionOpen(putOptionSymbol);

					// Initialize class attributes or remove the checkpoint file
					if (hasPosition) {
						this.lastTradedAt = lastTradedAt;
						this.isTradeOpen = true;
					} else {
						clearCheckPoint();
					}
				}
				System.out.println("Initialization Complete");
			} catch (Throwable e) {
				System.out.println("Error in initializing :" + this.getClass().getName());
			}
		}
	}

	public void doNext(double price) {
		LocalDateTime now = LocalDateTime.now();
		if (now.getMinute() % 15 == 0 && now.getSecond() == 0) {
			System.out.println("Price is :" + price);
		}
		if (!isTradeOpen && now.getHour() == 9 && now.getMinute() >= 20) {
			tradeOptions(price, Constants.TRANSACTION_TYPE_SELL);
			isTradeOpen = true;
			lastTradedAt = price;
			doCheckPointing((int) lastTradedAt);
		} else if (isTradeOpen && (price >= 1.01 * lastTradedAt || price <= .99 * lastTradedAt)) {
			tradeOptions(lastTradedAt, Constants.TRANSACTION_TYPE_BUY);
			tradeOptions(price, Constants.TRANSACTION_TYPE_SELL);
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

	private void doCheckPointing(int price) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(AppConstants.SSWA_CHECK_POINT_FILE));
			String toWrite = price > 0 ? price + "" : "";
			writer.write(toWrite);
			writer.close();
		} catch (IOException e) {
			System.out.println("Error in adding check point");
		}
	}

	private void clearCheckPoint() {
		doCheckPointing(0);
	}

	private void tradeOptions(double price, String transactionType) {
		placeOrders(getStrikeToTrade((int) price), transactionType);
	}

	private void placeOrders(int strike, String transactionType) {
		String putOptionSymbol = getPutOptionSymbol(strike);
		String callOptionSymbol = getCallOptionSymbol(strike);

		try {
			//Close Put position 
			if (isPositionOpen(putOptionSymbol)) {
				orderHandler.placeOrder(qty, putOptionSymbol, Constants.EXCHANGE_NFO, transactionType);
				System.out.println(qty + ":" + putOptionSymbol + ":" + transactionType);
			} else {
				System.out.println("Position already closed for :" + putOptionSymbol);
			}

			//Close call position
			if (isPositionOpen(callOptionSymbol)) {
				orderHandler.placeOrder(qty, callOptionSymbol, Constants.EXCHANGE_NFO, transactionType);
				System.out.println(qty + ":" + callOptionSymbol + ":" + transactionType);
			} else {
				System.out.println("Position already closed for :" + callOptionSymbol);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private boolean isPositionOpen(String tradingSymbol) throws IOException, KiteException {
		List<Position> positions = positionsProvider.getPositions().get("net");
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
