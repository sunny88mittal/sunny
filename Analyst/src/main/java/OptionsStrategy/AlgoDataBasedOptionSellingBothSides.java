package OptionsStrategy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Constants.StockSymbols;
import Entities.OptionsChain;

public class AlgoDataBasedOptionSellingBothSides implements IOptionsStrategy {

	private int stopLoss = 100;

	private boolean doStrikeDataCheck = false;

	private int trailingStopLoss = -1;

	public AlgoDataBasedOptionSellingBothSides(int stopLoss, boolean doStrikeDataCheck,
			int trailingStopLoss) {
		this.stopLoss = stopLoss;
		this.doStrikeDataCheck = doStrikeDataCheck;
		this.trailingStopLoss = trailingStopLoss;
	}

	public List<Trade> execute(String date) {
		List<Trade> trades = new ArrayList<Trade>();
		boolean isTradeOpen = false;
		boolean isReversalTradeOpen = false;

		for (int i = 0; i < 10000; i++) {
			OptionsChain optionsChain = OptionsDataProvider.getData(StockSymbols.BANKNIFTY.name, date, i);
			if (optionsChain == null) {
				break;
			}
			Timestamp ts = new Timestamp(optionsChain.timeStamp);
			int hours = ts.getHours();
			int minutes = ts.getMinutes();

			double callOIChange = OptionsChainHelper.getOpenInterestChange(optionsChain.callOptions);
			double putOIChange = OptionsChainHelper.getOpenInterestChange(optionsChain.putOptions);
			int price = (int) Double.parseDouble(optionsChain.price);

			// Open a trade at 9:30 AM
			if (!isTradeOpen && hours >= 9 && minutes >= 30) {
				Trade trade = initiateTrade(price, optionsChain, callOIChange, putOIChange);
				if (trade != null) {
					trades.add(trade);
					isTradeOpen = true;
				}
			}

			Trade newTrade = null;
			// Close the trade/s
			for (Trade trade : trades) {

				if (!isTradeOpen(trade)) {
					continue;
				}

				// If time over close
				if ((hours >= 15 && minutes >= 29) || hours >= 16) {
					System.out.println("Day ends");
					trade.exit = price;
					if (trade.ceEntryPrice > 1) {
						trade.ceExitPrice = OptionsChainHelper.getCEPrice(optionsChain, trade.strike);
					}

					if (trade.peEntryPrice > 1) {
						trade.peExitPrice = OptionsChainHelper.getPEPrice(optionsChain, trade.strike);
					}
				}

				// If stop loss hits
				if (trade.ceEntryPrice > 1
						&& trade.ceStopLoss <= OptionsChainHelper.getCEPrice(optionsChain, trade.strike)) {
					System.out.println(ts.toString() + " Stop Loss hits");
					trade.ceExitPrice = OptionsChainHelper.getCEPrice(optionsChain, trade.strike);
					trade.exit = price;
				}

				if (trade.peEntryPrice > 1
						&& trade.peStopLoss <= OptionsChainHelper.getPEPrice(optionsChain, trade.strike)) {
					trade.peExitPrice = OptionsChainHelper.getPEPrice(optionsChain, trade.strike);
					System.out.println(ts.toString() + " Stop Loss hits");
					trade.exit = price;
				}

				// Trail the stop loss
				if (this.trailingStopLoss > 0 && trade.ceEntryPrice > 1
						&& (trade.ceStopLoss - OptionsChainHelper.getCEPrice(optionsChain, trade.strike)) >= 2
								* this.trailingStopLoss) {
					trade.ceStopLoss = trade.ceStopLoss - this.trailingStopLoss;
				}

				if (this.trailingStopLoss > 0 && trade.peEntryPrice > 1
						&& (trade.peStopLoss - OptionsChainHelper.getPEPrice(optionsChain, trade.strike)) >= 2
								* this.trailingStopLoss) {
					trade.peStopLoss = trade.peStopLoss - this.trailingStopLoss;
				}

				// If data reversal happens
				if (trade.ceEntryPrice > 1 && putOIChange > callOIChange && !isReversalTradeOpen) {
					// System.out.println(ts.toString() + "Data reversal");
					newTrade = initiateTrade(price, optionsChain, callOIChange, putOIChange);
					isReversalTradeOpen = newTrade != null ? true : false;
				}

				if (trade.peEntryPrice > 1 && putOIChange < callOIChange && !isReversalTradeOpen) {
					// System.out.println(ts.toString() + "Data reversal");
					newTrade = initiateTrade(price, optionsChain, callOIChange, putOIChange);
					isReversalTradeOpen = newTrade != null ? true : false;
				}
			}

			if (newTrade != null) {
				trades.add(newTrade);
			}
		}

		return trades;
	}

	private static boolean isTradeOpen(Trade trade) {
		if (trade.ceEntryPrice > 1 && trade.ceExitPrice > 1) {
			return false;
		}

		if (trade.peEntryPrice > 1 && trade.peExitPrice > 1) {
			return false;
		}

		return true;
	}

	private Trade initiateTrade(int price, OptionsChain optionsChain, double callOIChange, double putOIChange) {
		int strike = (price / 100) * 100;
		double sellingPrice = 0;
		Trade trade = new Trade();
		trade.entry = price;
		Timestamp ts = new Timestamp(optionsChain.timeStamp);

		if (callOIChange > putOIChange) {
			strike = (price / 100) * 100 + 100;
			double strikeCEOIchange = OptionsChainHelper.getCEOIChange(optionsChain, strike);
			double strikePEOIchange = OptionsChainHelper.getPEOIChange(optionsChain, strike);
			if (this.doStrikeDataCheck && strikeCEOIchange < strikePEOIchange) {
				return null;
			}
			sellingPrice = OptionsChainHelper.getCEPrice(optionsChain, strike);
			trade.ceEntryPrice = sellingPrice;
			trade.ceStopLoss = sellingPrice + stopLoss;
			trade.strike = strike;
			System.out.println(ts.toString() + " Selling Call : " + strike + " at : " + sellingPrice);
		} else {
			double strikeCEOIchange = OptionsChainHelper.getCEOIChange(optionsChain, strike);
			double strikePEOIchange = OptionsChainHelper.getPEOIChange(optionsChain, strike);
			if (this.doStrikeDataCheck && strikePEOIchange < strikeCEOIchange) {
				return null;
			}
			sellingPrice = OptionsChainHelper.getPEPrice(optionsChain, strike);
			trade.peEntryPrice = sellingPrice;
			trade.peStopLoss = sellingPrice + stopLoss;
			trade.strike = strike;
			System.out.println(ts.toString() + " Selling Put : " + strike + " at : " + sellingPrice);
		}

		return trade;
	}

	public static void main(String args[]) {
		AlgoDataBasedOptionSellingBothSides dbos = new AlgoDataBasedOptionSellingBothSides(
				100, false, 100);
		List<Trade> trades = dbos.execute("20-04-2022");
		int netProfit = 0;
		for (Trade trade : trades) {
			netProfit += trade.getNetPoints();
			System.out.println(trade);
		}
		System.out.println("Net profit is : " + 25 * netProfit);
	}

	@Override
	public String getName() {
		return "DataBasedOptionSellingBothSidesWithTrailingSL" + " WithSL:" + stopLoss + " StrikeCheck:"
				+ doStrikeDataCheck + " TrailingSL:" + trailingStopLoss;
	}
}
