package OptionsStrategy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Constants.StockSymbols;
import Entities.OptionsChain;

public class AlgoDataBasedOptionSpreads implements IOptionsStrategy {

	private int stopLoss = 0;

	private int spreadGap = 0;

	private int trailingStopLoss = 1000;

	private int distanceFromSpot = 0;

	public AlgoDataBasedOptionSpreads(int spreadGap, int stopLoss, int trailingStopLoss) {
		this.spreadGap = spreadGap;
		this.stopLoss = stopLoss;
		this.trailingStopLoss = trailingStopLoss;
	}

	public AlgoDataBasedOptionSpreads(int spreadGap, int stopLoss, int trailingStopLoss, int distanceFromSpot) {
		this(spreadGap, stopLoss, trailingStopLoss);
		this.distanceFromSpot = distanceFromSpot;
	}

	public List<Trade> execute(String date) {
		List<Trade> trades = new ArrayList<Trade>();
		Trade buyTrade = null;
		Trade sellTrade = null;
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
				double buyingPrice = 0;
				buyTrade = new Trade();
				buyTrade.entry = price;
				buyTrade.isSellTrade = false;

				sellTrade = new Trade();
				sellTrade.entry = price;

				if (callOIChange < putOIChange) {
					strike += 100 + distanceFromSpot;
					buyingPrice = OptionsChainHelper.getCEPrice(optionsChain, strike);
					buyTrade.ceEntryPrice = buyingPrice;
					buyTrade.ceStopLoss = buyingPrice - stopLoss;
					buyTrade.strike = strike;
					System.out.println(ts.toString() + " Buying Call : " + strike + " at : " + buyingPrice);

					if (spreadGap > 0) {
						strike += spreadGap;
						double sellingPrice = OptionsChainHelper.getCEPrice(optionsChain, strike);
						sellTrade.ceEntryPrice = sellingPrice;
						sellTrade.strike = strike;
					}
				} else {
					strike -= distanceFromSpot;
					buyingPrice = OptionsChainHelper.getPEPrice(optionsChain, strike);
					buyTrade.peEntryPrice = buyingPrice;
					buyTrade.peStopLoss = buyingPrice - stopLoss;
					buyTrade.strike = strike;
					System.out.println(ts.toString() + " Buying Put : " + strike + " at : " + buyingPrice);

					if (spreadGap > 0) {
						strike -= spreadGap;
						double sellingPrice = OptionsChainHelper.getPEPrice(optionsChain, strike);
						sellTrade.peEntryPrice = sellingPrice;
						sellTrade.strike = strike;
					}
				}

				trades.add(buyTrade);
				if (sellTrade != null) {
					trades.add(sellTrade);
				}
				isTradeOpen = true;
			}

			// Close the trade
			if (isTradeOpen) {
				// If time over close
				if (hours >= 15 && minutes >= 29) {
					System.out.println("Day ends");
					buyTrade.exit = price;
					sellTrade.exit = price;
					if (buyTrade.ceEntryPrice > 1) {
						buyTrade.ceExitPrice = OptionsChainHelper.getCEPrice(optionsChain, buyTrade.strike);
						if (sellTrade != null) {
							sellTrade.ceExitPrice = OptionsChainHelper.getCEPrice(optionsChain, sellTrade.strike);
						}
					}

					if (buyTrade.peEntryPrice > 1) {
						buyTrade.peExitPrice = OptionsChainHelper.getPEPrice(optionsChain, buyTrade.strike);
						if (sellTrade != null) {
							sellTrade.peExitPrice = OptionsChainHelper.getPEPrice(optionsChain, sellTrade.strike);
						}
					}

					break;
				}

				// If stop loss hits
				if (buyTrade.ceEntryPrice > 1
						&& buyTrade.ceStopLoss >= OptionsChainHelper.getCEPrice(optionsChain, buyTrade.strike)) {
					if (OptionsChainHelper.getCEPrice(optionsChain, buyTrade.strike) > .5) {
						System.out.println(ts.toString() + " Stop Loss hits");
						buyTrade.ceExitPrice = OptionsChainHelper.getCEPrice(optionsChain, buyTrade.strike);
						buyTrade.exit = price;
						if (sellTrade != null) {
							sellTrade.ceExitPrice = OptionsChainHelper.getCEPrice(optionsChain, sellTrade.strike);
							sellTrade.exit = price;
						}
						break;
					}
				}

				if (buyTrade.peEntryPrice > 1
						&& buyTrade.peStopLoss >= OptionsChainHelper.getPEPrice(optionsChain, buyTrade.strike)) {
					if (OptionsChainHelper.getPEPrice(optionsChain, buyTrade.strike) > .5) {
						System.out.println(ts.toString() + " Stop Loss hits");
						buyTrade.peExitPrice = OptionsChainHelper.getPEPrice(optionsChain, buyTrade.strike);
						buyTrade.exit = price;
						if (sellTrade != null) {
							sellTrade.peExitPrice = OptionsChainHelper.getPEPrice(optionsChain, sellTrade.strike);
							sellTrade.exit = price;
						}
						break;
					}
				}
			}
		}

		return trades;
	}

	public static void main(String args[]) {
		AlgoDataBasedOptionSpreads dbos = new AlgoDataBasedOptionSpreads(0, 100, 100);
		List<Trade> trades = dbos.execute("16-06-2022");
		int netProfit = 0;
		for (Trade trade : trades) {
			netProfit += trade.getNetPoints();
			System.out.println(trade);
		}
		System.out.println("Net profit is : " + 25 * netProfit);
	}

	@Override
	public String getName() {
		return "DataBasedOptionSpreads" + " WithSL:" + stopLoss + " SpreadGap:" + spreadGap + " TrailingSL:"
				+ trailingStopLoss + " DistanceFromSpot:" + distanceFromSpot;
	}
}