package Zerodha.Trader.Strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.kiteconnect.utils.Constants;
import com.zerodhatech.models.Order;
import com.zerodhatech.models.Position;

import Zerodha.Trader.Core.AppConstants;
import Zerodha.Trader.Core.KiteUser;
import Zerodha.Trader.Core.TradingSymbolHelper;
import Zerodha.Trader.Logging.Logger;
import Zerodha.Trader.Messaging.TelegramService;
import Zerodha.Trader.Services.KiteHandler;

public class ShortStraddleWithFixedStopLoss implements IStrategy {

	private boolean isTradeOpen;

	private double lastTradedAt;

	private String optionDateValue;

	private List<KiteUser> kiteUsers;

	private int MAX_LOSS_PER_UNIT = 160;

	private static String SSWSL_CHECK_POINT_FILE = "..\\SSWFSLCheckPointFile.txt";

	public ShortStraddleWithFixedStopLoss(String optionDateValue, List<KiteUser> kiteUsers) {
		this.optionDateValue = optionDateValue;
		this.kiteUsers = kiteUsers;
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
		try {
			LocalDateTime now = LocalDateTime.now();
			int maxAllowedLoss = kiteUsers.get(0).qty * MAX_LOSS_PER_UNIT;

			if (now.getMinute() % 15 == 0 && now.getSecond() == 0) {
				Logger.print(this.getClass(), "Price is :" + price);
			}

			if (!isTradeOpen && now.getHour() == 9 && now.getMinute() >= 20) {
				tradeOptions(price, Constants.TRANSACTION_TYPE_SELL);
				isTradeOpen = true;
				lastTradedAt = price;
				doCheckPointing((int) lastTradedAt);
				TelegramService.sendMessage("Traded short starddle at strike " + getStrikeToTrade((int) price));
			} else if (isTradeOpen && (now.getHour() == 15 && now.getMinute() >= 29)) {
				closeTrades();
				TelegramService.sendMessage("Closing short starddle as time is over");
			} else if (isTradeOpen && now.getSecond() % 15 == 0 && getTotalLoss() >= maxAllowedLoss) {
				closeTrades();
				TelegramService.sendMessage("Hit maximum loss for the day, closing all trades");
			}
		} catch (Throwable ex) {
			TelegramService.sendMessage("Error processing the tick");
			Logger.print(this.getClass(), "Error processing the tick");
		}
	}

	private int getTotalLoss() throws IOException, KiteException {
		int toalLoss = 0;
		int strikeTraded = getStrikeToTrade((int) lastTradedAt);
		Map<String, List<Position>> positions = kiteUsers.get(0).kiteHandler.getPositions();
		List<Position> intradayPositions = positions.get("net");
		for (Position position : intradayPositions) {
			if (position.tradingSymbol.contains("" + strikeTraded)) {
				toalLoss += position.pnl;
			}
		}

		return toalLoss;
	}

	private void closeTrades() {
		tradeOptions(lastTradedAt, Constants.TRANSACTION_TYPE_BUY);
		isTradeOpen = false;
		lastTradedAt = 0;
		clearCheckPoint();
	}

	public void disconnectedFromBroker() {
		LocalDateTime now = LocalDateTime.now();
		TelegramService.sendMessage("Disconnected from broker");
		if (isTradeOpen && now.getHour() == 15 && now.getMinute() >= 29) {
			TelegramService.sendMessage("Closing short starddle as time is over");
			tradeOptions(lastTradedAt, Constants.TRANSACTION_TYPE_BUY);
			isTradeOpen = false;
			lastTradedAt = 0;
			clearCheckPoint();
		}
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

	private void tradeOptions(double price, String transactionType) {
		placeNRMLOrders(getStrikeToTrade((int) price), transactionType);
	}

	private void placeNRMLOrders(int strike, String transactionType) {
		String putOptionSymbol = getPutOptionSymbol(strike);
		String callOptionSymbol = getCallOptionSymbol(strike);
		boolean isBuyOrder = Constants.TRANSACTION_TYPE_BUY.equals(transactionType);
		boolean isSellOrder = Constants.TRANSACTION_TYPE_SELL.equals(transactionType);
		List<Order> orders = new ArrayList<Order>();

		for (KiteUser kiteUser : kiteUsers) {
			KiteHandler kiteHandler = kiteUser.kiteHandler;
			int qty = kiteUser.qty;
			try {
				// Trade Put position
				if ((isBuyOrder && isPositionOpen(putOptionSymbol, kiteHandler)) || isSellOrder) {
					Order order = kiteHandler.placeMarketOrder(qty, putOptionSymbol, Constants.EXCHANGE_NFO,
							transactionType);
					orders.add(order);
					Logger.print(this.getClass(), qty + ":" + putOptionSymbol + ":" + transactionType);
				} else {
					System.out.println("Position already closed for :" + putOptionSymbol);
				}

				// Close call position
				if ((isBuyOrder && isPositionOpen(callOptionSymbol, kiteHandler)) || isSellOrder) {
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
		}
	}

	private boolean isPositionOpen(String tradingSymbol, KiteHandler kiteHandler) throws IOException, KiteException {
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
