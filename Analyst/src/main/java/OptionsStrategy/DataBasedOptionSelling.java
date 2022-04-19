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

public class DataBasedOptionSelling {

	private List<Trade> trades = new ArrayList<Trade>();

	public List<Trade> execute(String date) {
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
					sellingPrice = OptionsChainHelper.getCEPrice(optionsChain, strike);
					trade.ceEntryPrice = sellingPrice;
					trade.ceStopLoss = sellingPrice + 100;
					trade.strike = strike;
					System.out.println("Selling Call : " + strike + " at : " + sellingPrice);
				} else {
					sellingPrice = OptionsChainHelper.getPEPrice(optionsChain, strike);
					trade.peEntryPrice = sellingPrice;
					trade.peStopLoss = sellingPrice + 100;
					trade.strike = strike;
					System.out.println("Selling Put : " + strike + " at : " + sellingPrice);
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
					System.out.println("Stop Loss hits");
					trade.ceExitPrice = OptionsChainHelper.getCEPrice(optionsChain, trade.strike);
					trade.exit = price;
					break;
				}

				if (trade.peEntryPrice > 1
						&& trade.peStopLoss <= OptionsChainHelper.getPEPrice(optionsChain, trade.strike)) {
					trade.peExitPrice = OptionsChainHelper.getPEPrice(optionsChain, trade.strike);
					System.out.println("Stop Loss hits");
					trade.exit = price;
					break;
				}

				// If data reversal happens
				if (trade.ceEntryPrice > 1 && putOIChange > callOIChange && trade.entry < price) {
					System.out.println("Data reversal");
					trade.ceExitPrice = OptionsChainHelper.getCEPrice(optionsChain, trade.strike);
					trade.exit = price;
					break;
				}
				
				if (trade.peEntryPrice > 1 && putOIChange < callOIChange &&  trade.entry > price) {
					System.out.println("Data reversal");
					trade.exit = price;
					trade.peExitPrice = OptionsChainHelper.getCEPrice(optionsChain, trade.strike);
					break;
				}
			}
		}

		return trades;
	}
	
	public static void main(String args[]) {
		DataBasedOptionSelling dbos = new DataBasedOptionSelling();
		List<Trade> trades = dbos.execute("11-03-2022");
		int netProfit = 0;
		for (Trade trade : trades) {
			netProfit += trade.getNetPoints();
			System.out.println(trade);
		}
		System.out.println("Net profit is : " + 25 * netProfit);
	}
}
