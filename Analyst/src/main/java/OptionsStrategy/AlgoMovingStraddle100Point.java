package OptionsStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Constants.StockSymbols;
import Entities.OptionsChain;

public class AlgoMovingStraddle100Point implements IOptionsStrategy {

	private Set<Long> strikesExecuted = new HashSet<Long>();

	private List<Trade> trades = new ArrayList<Trade>();

	public List<Trade> execute(String date) {
		int i = 0;
		for (i = 0; i < 10000; i++) {
			OptionsChain optionsChain = OptionsDataProvider.getData(StockSymbols.BANKNIFTY.name, date, i);
			if (optionsChain == null) {
				break;
			}

			// Get strike to execute
			double price = Double.parseDouble(optionsChain.price);
			int remainder = (int) (price % 100);
			long strike = (long) (price - remainder);
			if (remainder > 50) {
				strike += 100;
			}

			// Execute new Straddle
			if (!strikesExecuted.contains(strike) && price > strike && Math.abs(price - strike) <= 20) {
				Trade trade = new Trade();
				trade.entry = price;
				trade.strike = strike;
				trade.ceEntryPrice = OptionsChainHelper.getCEPrice(optionsChain, strike);
				trade.ceStopLoss = Double.MAX_VALUE;
				trade.peEntryPrice = OptionsChainHelper.getPEPrice(optionsChain, strike);
				trade.peStopLoss = Double.MAX_VALUE;
				trades.add(trade);
				strikesExecuted.add(strike);
			}

			// Close trades at 1% change
			for (Trade trade : trades) {
				if (trade.exit < 1) {
					if (price >= 1.01 * trade.entry) {
						trade.ceExitPrice = OptionsChainHelper.getCEPrice(optionsChain, trade.strike);
						trade.peStopLoss = trade.peEntryPrice;
						trade.exit = price;
					}

					if (price <= .99 * trade.entry) {
						trade.peExitPrice = OptionsChainHelper.getPEPrice(optionsChain, trade.strike);
						trade.ceStopLoss = trade.ceEntryPrice;
						trade.exit = price;
					}
				}
			}

			// Close trades with stop loss hit
			for (Trade trade : trades) {
				if (trade.ceStopLoss <= 10000 && trade.ceExitPrice == 0) {
					double cePrice = OptionsChainHelper.getCEPrice(optionsChain, trade.strike);
					if (cePrice >= trade.ceStopLoss) {
						trade.ceExitPrice = cePrice;
					}
				}

				if (trade.peStopLoss <= 10000 && trade.peExitPrice == 0) {
					double pePrice = OptionsChainHelper.getPEPrice(optionsChain, trade.strike);
					if (pePrice >= trade.peStopLoss) {
						trade.peExitPrice = pePrice;
					}
				}
			}
		}

		// Close open trades
		i -= 1;
		OptionsChain optionsChain = OptionsDataProvider.getData(StockSymbols.BANKNIFTY.name, date, i);
		for (Trade trade : trades) {
			double strike = trade.strike;
			if (trade.ceExitPrice == 0) {
				trade.ceExitPrice = OptionsChainHelper.getCEPrice(optionsChain, strike);
			}

			if (trade.peExitPrice == 0) {
				trade.peExitPrice = OptionsChainHelper.getPEPrice(optionsChain, strike);
			}

			if (trade.exit == 0) {
				trade.exit = Double.parseDouble(optionsChain.price);
			}
		}

		return trades;
	}

	public static void main(String args[]) {
		AlgoMovingStraddle100Point ms = new AlgoMovingStraddle100Point();
		List<Trade> trades = ms.execute("22-04-2022");
		int netProfit = 0;
		for (Trade trade : trades) {
			netProfit += trade.getNetPoints();
			System.out.println(trade);
		}
		System.out.println("Net profit is : " + 25 * netProfit);
	}
}
