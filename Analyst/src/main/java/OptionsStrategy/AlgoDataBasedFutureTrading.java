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

public class AlgoDataBasedFutureTrading implements IOptionsStrategy {

	private int stopLoss = 100;

	private int trailingStopLoss = -1;

	private boolean stopOnDataReversal = false;

	public AlgoDataBasedFutureTrading(int stopLoss, int trailingStopLoss, boolean stopOnDataReversal) {
		this.stopLoss = stopLoss;
		this.trailingStopLoss = trailingStopLoss;
		this.stopOnDataReversal = stopOnDataReversal;
	}

	public List<Trade> execute(String date) {
		List<Trade> trades = new ArrayList<Trade>();
		boolean isTradeOpen = false;

		for (int i = 0; i < 10000; i++) {
			OptionsChain optionsChain = OptionsDataProvider.getData(StockSymbols.BANKNIFTY.name, date, i);

			if (optionsChain == null) {
				continue;
			}
			Timestamp ts = new Timestamp(optionsChain.timeStamp);

			int hours = ts.getHours();
			int minutes = ts.getMinutes();

			double callOIChange = OptionsChainHelper.getOpenInterestChange(optionsChain.callOptions);
			double putOIChange = OptionsChainHelper.getOpenInterestChange(optionsChain.putOptions);
			int price = (int) Double.parseDouble(optionsChain.price);

			// Open a trade
			if (!isTradeOpen && hours >= 9 && minutes >= 30) {
				int strike = (price / 100) * 100;
				Trade trade = new Trade();
				trade.entry = price;

				if (callOIChange > putOIChange) {
					strike += 100;
					double strikeCEOIchange = OptionsChainHelper.getCEOIChange(optionsChain, strike);
					double strikePEOIchange = OptionsChainHelper.getPEOIChange(optionsChain, strike);
					if (strikeCEOIchange < strikePEOIchange) {
						continue;
					}

					trade.ceEntryPrice = price;
					trade.ceStopLoss = price + stopLoss;
					trade.strike = strike;
					System.out.println(ts.toString() + " Selling Future at : " + trade.ceEntryPrice);
				} else {
					double strikeCEOIchange = OptionsChainHelper.getCEOIChange(optionsChain, strike);
					double strikePEOIchange = OptionsChainHelper.getPEOIChange(optionsChain, strike);
					if (strikePEOIchange < strikeCEOIchange) {
						continue;
					}

					trade.ceEntryPrice = price;
					trade.ceStopLoss = price - stopLoss;
					trade.strike = strike;
					trade.isSellTrade = false;
					System.out.println(ts.toString() + " Buying Future at : " + trade.ceEntryPrice);
				}

				trades.add(trade);
				isTradeOpen = true;
			}

			// Close the trade
			if (isTradeOpen) {
				Trade trade = trades.get(0);
				boolean isSellTrade = trade.isSellTrade;

				// If time over close
				if ((hours >= 15 && minutes >= 29) || hours >= 16) {
					System.out.println("Day ends");
					trade.exit = price;
					trade.ceExitPrice = price;
					break;
				}

				// If stop loss hits
				if (isSellTrade && trade.ceStopLoss <= price) {
					System.out.println(ts.toString() + " Stop Loss hits");
					trade.ceExitPrice = price;
					trade.exit = price;
					break;
				}

				if (!isSellTrade && trade.ceStopLoss >= price) {
					System.out.println(ts.toString() + " Stop Loss hits");
					trade.ceExitPrice = price;
					trade.exit = price;
					break;
				}

				// If data reversal happens
				if (isSellTrade && this.stopOnDataReversal && putOIChange > callOIChange && trade.entry < price) {
					System.out.println("Data reversal");
					trade.ceExitPrice = price;
					trade.exit = price;
					break;
				}

				if (!isSellTrade && this.stopOnDataReversal && callOIChange > putOIChange && trade.entry > price) {
					System.out.println("Data reversal");
					trade.ceExitPrice = price;
					trade.exit = price;
					break;
				}

				// Trail the stop loss
				if (isSellTrade && this.trailingStopLoss > 0
						&& (trade.ceStopLoss - price) >= 2 * this.trailingStopLoss) {
					trade.ceStopLoss = trade.ceStopLoss - this.trailingStopLoss;
				}

				if (!isSellTrade && this.trailingStopLoss > 0
						&& (price - trade.ceStopLoss) >= 2 * this.trailingStopLoss) {
					trade.ceStopLoss = trade.ceStopLoss + this.trailingStopLoss;
				}
			}
		}

		return trades;
	}

	public static void main(String args[]) {
		AlgoDataBasedFutureTrading dbos = new AlgoDataBasedFutureTrading(200, -1, false);
		List<Trade> trades = dbos.execute("03-06-2022");
		int netProfit = 0;
		for (Trade trade : trades) {
			netProfit += trade.getNetPoints();
			System.out.println(trade);
		}
		System.out.println("Net profit is : " + 25 * netProfit);
	}

	@Override
	public String getName() {
		return "DataBasedFutureTrading" + " WithSL:" + stopLoss + " TrailingSL:" + trailingStopLoss
				+ " StopOnDataReversal:" + stopOnDataReversal;
	}
}
