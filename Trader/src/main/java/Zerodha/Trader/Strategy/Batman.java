package Zerodha.Trader.Strategy;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.kiteconnect.utils.Constants;
import com.zerodhatech.models.Order;
import com.zerodhatech.models.Position;

import Zerodha.Trader.Core.KiteUser;
import Zerodha.Trader.Logging.Logger;
import Zerodha.Trader.Messaging.TelegramService;

public class Batman implements IStrategy {

	private boolean isTradeOpen;

	private int lastTradedAt;

	private int range;

	private String optionDateValue;

	private List<KiteUser> kiteUsers;

	private int MAX_LOSS_PER_UNIT = 79;

	private static String CHECK_POINT_FILE = "..\\BatmanCheckPointFile.txt";

	public Batman(String optionDateValue, List<KiteUser> kiteUsers) {
		this.optionDateValue = optionDateValue;
		this.kiteUsers = kiteUsers;
		this.lastTradedAt = 0;
		this.range = 0;
		this.isTradeOpen = false;
	}

	public void initialize() {
		File file = new File(CHECK_POINT_FILE);
		if (file.exists()) {
			try (Scanner sc = new Scanner(file)) {
				if (sc.hasNextLine()) {
					// Read the banknifty price at which last trade happened
					String line = sc.nextLine();
					String tokens[] = line.split(",");
					int lastTradedAt = Integer.parseInt(tokens[0]);
					int range = Integer.parseInt(tokens[1]);
					this.lastTradedAt = lastTradedAt;
					this.range = range;
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
			int maxAllowedLoss = -kiteUsers.get(0).qty * MAX_LOSS_PER_UNIT;

			if (now.getMinute() % 15 == 0 && now.getSecond() == 0) {
				Logger.print(this.getClass(), "Price is :" + price);
			}

			if (!isTradeOpen && now.getHour() == 9 && now.getMinute() >= 20) {
				openTrades(price);
			} else if (isTradeOpen && (now.getHour() == 15 && now.getMinute() >= 29)) {
				closeTrades();
				TelegramService.sendMessage("Closing strategy as time is over");
			} else if (isTradeOpen && now.getSecond() % 15 == 0) {
				int totalProfit = getTotalProfit();
				if (totalProfit >= maxAllowedLoss) {
					closeTrades();
					TelegramService.sendMessage("Hit maximum loss for the day, closed all trades");
				}
				if (now.getMinute() % 15 == 0 && now.getSecond() == 0) {
					Logger.print(this.getClass(), "Net profit at present is : " + totalProfit);
				}
			}
		} catch (Throwable ex) {
			TelegramService.sendMessage("Error processing the tick");
			Logger.print(this.getClass(), "Error processing the tick");
		}
	}

	private int getTotalProfit() throws IOException, KiteException {
		int totalProfit = 0;
		Map<String, List<Position>> positions = kiteUsers.get(0).kiteHandler.getPositions();
		List<Position> intradayPositions = positions.get("net");

		String straddleCESymbol = StrategyUtil.getCallOptionSymbol(lastTradedAt, optionDateValue);
		String straddlePESymbol = StrategyUtil.getPutOptionSymbol(lastTradedAt, optionDateValue);
		String strangleCESymbol = StrategyUtil.getCallOptionSymbol(lastTradedAt + range, optionDateValue);
		String stranglePESymbol = StrategyUtil.getPutOptionSymbol(lastTradedAt - range, optionDateValue);

		for (Position position : intradayPositions) {
			String symbol = position.tradingSymbol;
			if (symbol.contains(straddleCESymbol) || symbol.contains(straddlePESymbol)
					|| symbol.contains(strangleCESymbol) || symbol.contains(stranglePESymbol)) {
				totalProfit += position.pnl;
			}
		}

		return totalProfit;
	}

	private void openTrades(double price) throws Throwable {
		int strike = StrategyUtil.getStrikeToTrade(price);
		KiteUser user = kiteUsers.get(0);

		// Sell straddle
		String ceSymbol = StrategyUtil.getCallOptionSymbol(strike, optionDateValue);
		String peSymbol = StrategyUtil.getPutOptionSymbol(strike, optionDateValue);
		Order ceOrder = StrategyUtil.placeNRMLOrders(user, user.qty, ceSymbol, Constants.TRANSACTION_TYPE_BUY, false);
		Order peOrder = StrategyUtil.placeNRMLOrders(user, user.qty, peSymbol, Constants.TRANSACTION_TYPE_BUY, false);

		// Buy strangle
		float straddlePrice = Float.parseFloat(ceOrder.averagePrice) + Float.parseFloat(peOrder.averagePrice);
		int range = (int) (100 * (straddlePrice / 100));
		ceSymbol = StrategyUtil.getCallOptionSymbol(strike + range, optionDateValue);
		peSymbol = StrategyUtil.getPutOptionSymbol(strike - range, optionDateValue);
		ceOrder = StrategyUtil.placeNRMLOrders(user, 2 * user.qty, ceSymbol, Constants.TRANSACTION_TYPE_SELL, false);
		peOrder = StrategyUtil.placeNRMLOrders(user, 2 * user.qty, peSymbol, Constants.TRANSACTION_TYPE_SELL, false);

		isTradeOpen = true;
		lastTradedAt = strike;
		this.range = range;
		StrategyUtil.doCheckPointing(strike + "," + range, CHECK_POINT_FILE);

		TelegramService.sendMessage("Traded Batman at strike " + strike + "  with range " + range);
	}

	private void closeTrades() throws Throwable {
		KiteUser user = kiteUsers.get(0);

		// Sell straddle
		String ceSymbol = StrategyUtil.getCallOptionSymbol(lastTradedAt, optionDateValue);
		String peSymbol = StrategyUtil.getPutOptionSymbol(lastTradedAt, optionDateValue);
		StrategyUtil.placeNRMLOrders(user, user.qty, ceSymbol, Constants.TRANSACTION_TYPE_SELL, true);
		StrategyUtil.placeNRMLOrders(user, user.qty, peSymbol, Constants.TRANSACTION_TYPE_SELL, true);

		// Buy strangle
		ceSymbol = StrategyUtil.getCallOptionSymbol(lastTradedAt + range, optionDateValue);
		peSymbol = StrategyUtil.getPutOptionSymbol(lastTradedAt - range, optionDateValue);
		StrategyUtil.placeNRMLOrders(user, 2 * user.qty, ceSymbol, Constants.TRANSACTION_TYPE_BUY, true);
		StrategyUtil.placeNRMLOrders(user, 2 * user.qty, peSymbol, Constants.TRANSACTION_TYPE_BUY, true);
		isTradeOpen = false;
		lastTradedAt = 0;
		StrategyUtil.doCheckPointing("", CHECK_POINT_FILE);
	}

	public void disconnectedFromBroker() {
		LocalDateTime now = LocalDateTime.now();
		TelegramService.sendMessage("Disconnected from broker");
		if (isTradeOpen && now.getHour() == 15 && now.getMinute() >= 29) {
			TelegramService.sendMessage("Closing strategy as time is over");
			try {
				closeTrades();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			isTradeOpen = false;
			lastTradedAt = 0;
			StrategyUtil.doCheckPointing("", CHECK_POINT_FILE);
		}
	}
}
