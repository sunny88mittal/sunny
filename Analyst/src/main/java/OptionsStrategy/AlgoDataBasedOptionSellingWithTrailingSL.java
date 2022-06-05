package OptionsStrategy;

/**
 * Sell an option at 9:30 AM based upon which side more options are written
 * Close the trade
 *   1. If stop loss hits
 *   2. If data reverses
 *   3. If day ends i.e 3:29 PM 
 */

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Constants.StockSymbols;
import Entities.OptionsChain;

public class AlgoDataBasedOptionSellingWithTrailingSL implements IOptionsStrategy {

	private int stopLoss = 100;

	private int trailingStopLoss = 1000;

	public AlgoDataBasedOptionSellingWithTrailingSL(int stopLoss, int trailingStopLoss) {
		this.stopLoss = stopLoss;
		this.trailingStopLoss = trailingStopLoss;
	}

	public List<Trade> execute(String date) {
		List<Trade> trades = new ArrayList<Trade>();
		boolean isTradeOpen = false;

		for (int i = 0; i < 10000; i++) {
			OptionsChain optionsChain = OptionsDataProvider.getData(StockSymbols.BANKNIFTY.name, date, i);
			Timestamp ts = new Timestamp(optionsChain.timeStamp);
			int hours = ts.getHours();
			int minutes = ts.getMinutes();

			double callOIChange = OptionsChainHelper.getOpenInterestChange(optionsChain.callOptions);
			double putOIChange = OptionsChainHelper.getOpenInterestChange(optionsChain.putOptions);
			int price = (int) Double.parseDouble(optionsChain.price);

			// Open a trade
			if (!isTradeOpen && hours >= 9 && minutes >= 30) {
				int strike = (price / 100) * 100;
				double sellingPrice = 0;
				Trade trade = new Trade();
				trade.entry = price;

				if (callOIChange > putOIChange) {
					strike = (price / 100) * 100 + 100;

					double strikeCEOIchange = OptionsChainHelper.getCEOIChange(optionsChain, strike);
					double strikePEOIchange = OptionsChainHelper.getPEOIChange(optionsChain, strike);
					if (strikeCEOIchange < strikePEOIchange) {
						continue;
					}

					sellingPrice = OptionsChainHelper.getCEPrice(optionsChain, strike);
					trade.ceEntryPrice = sellingPrice;
					trade.ceStopLoss = sellingPrice + stopLoss;
					trade.strike = strike;
					System.out.println(ts.toString() + " Selling Call : " + strike + " at : " + sellingPrice);
				} else {
					double strikeCEOIchange = OptionsChainHelper.getCEOIChange(optionsChain, strike);
					double strikePEOIchange = OptionsChainHelper.getPEOIChange(optionsChain, strike);
					if (strikePEOIchange < strikeCEOIchange) {
						continue;
					}

					sellingPrice = OptionsChainHelper.getPEPrice(optionsChain, strike);
					trade.peEntryPrice = sellingPrice;
					trade.peStopLoss = sellingPrice + stopLoss;
					trade.strike = strike;
					System.out.println(ts.toString() + " Selling Put : " + strike + " at : " + sellingPrice);
				}

				trades.add(trade);
				isTradeOpen = true;
			}

			// Close the trade
			if (isTradeOpen) {
				Trade trade = trades.get(0);

				// If time over close
				if (hours >= 15 && minutes >= 29) {
					System.out.println("Day ends");
					trade.exit = price;
					if (trade.ceEntryPrice > 1) {
						trade.ceExitPrice = OptionsChainHelper.getCEPrice(optionsChain, trade.strike);
					}

					if (trade.peEntryPrice > 1) {
						trade.peExitPrice = OptionsChainHelper.getPEPrice(optionsChain, trade.strike);
					}

					break;
				}

				// If stop loss hits
				if (trade.ceEntryPrice > 1
						&& trade.ceStopLoss <= OptionsChainHelper.getCEPrice(optionsChain, trade.strike)) {
					System.out.println(ts.toString() + " Stop Loss hits");
					trade.ceExitPrice = OptionsChainHelper.getCEPrice(optionsChain, trade.strike);
					trade.exit = price;
					break;
				}

				if (trade.peEntryPrice > 1
						&& trade.peStopLoss <= OptionsChainHelper.getPEPrice(optionsChain, trade.strike)) {
					trade.peExitPrice = OptionsChainHelper.getPEPrice(optionsChain, trade.strike);
					System.out.println(ts.toString() + " Stop Loss hits");
					trade.exit = price;
					break;
				}

				// Trail the stop loss
				if (trade.ceEntryPrice > 1
						&& (trade.ceStopLoss - OptionsChainHelper.getCEPrice(optionsChain, trade.strike)) >= 2
								* this.trailingStopLoss) {
					trade.ceStopLoss = trade.ceStopLoss - this.trailingStopLoss;
				}

				if (trade.peEntryPrice > 1
						&& (trade.peStopLoss - OptionsChainHelper.getPEPrice(optionsChain, trade.strike)) >= 2
								* this.trailingStopLoss) {
					trade.peStopLoss = trade.peStopLoss - this.trailingStopLoss;
				}
			}
		}

		return trades;
	}

	public static void main(String args[]) {
		AlgoDataBasedOptionSellingWithTrailingSL dbos = new AlgoDataBasedOptionSellingWithTrailingSL(100, 1000);
		List<Trade> trades = dbos.execute("25-04-2022");
		int netProfit = 0;
		for (Trade trade : trades) {
			netProfit += trade.getNetPoints();
			System.out.println(trade);
		}
		System.out.println("Net profit is : " + 25 * netProfit);
	}

	@Override
	public String getName() {
		return "DataBasedOptionSellingWithTrailingSL" + " WithSL:" + stopLoss + " TrailingSL:" + trailingStopLoss;
	}
}
