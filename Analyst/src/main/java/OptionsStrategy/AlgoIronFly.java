package OptionsStrategy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Constants.StockSymbols;
import Entities.OptionsChain;

public class AlgoIronFly implements IOptionsStrategy {

	@Override
	public List<Trade> execute(String date) {
		List<Trade> trades = new ArrayList<Trade>();
		
		boolean isTradeOpen = false;
		Trade shortStraddleTrade = null;
		List<Trade> longStrangleTrades = null;

		for (int i = 0; i < 10000; i++) {
			OptionsChain optionsChain = OptionsDataProvider.getData(StockSymbols.BANKNIFTY.name, date, i);
			if (optionsChain == null) {
				break;
			}

			Timestamp ts = new Timestamp(optionsChain.timeStamp);
			int hours = ts.getHours();
			int minutes = ts.getMinutes();
			int price = (int) Double.parseDouble(optionsChain.price);

			// Initial trade
			if (!isTradeOpen && hours >= 9 && minutes >= 20) {
				shortStraddleTrade = doShortStraddle(price, optionsChain);
				int combinedPremimum = (int) (shortStraddleTrade.ceEntryPrice + shortStraddleTrade.peEntryPrice);
				int shortStraddleStrike = (int) shortStraddleTrade.strike;

				longStrangleTrades = doLongStrangle(shortStraddleStrike, combinedPremimum, optionsChain);
				trades.add(shortStraddleTrade);
				trades.addAll(longStrangleTrades);
				isTradeOpen = true;
			}

			// Closing the trade at market close
			if (shortStraddleTrade != null && hours >= 15 && minutes >= 29) {
				for (Trade trade : trades) {
					closeTrade(trade, price, optionsChain);
				}
				break;
			}
		}

		return trades;
	}

	private Trade doShortStraddle(int price, OptionsChain optionsChain) {
		Trade trade = new Trade();

		int strike = (price / 100) * 100;
		if (price % 100 > 50) {
			strike += 100;
		}

		trade.entry = price;
		trade.strike = strike;
		trade.ceEntryPrice = OptionsChainHelper.getCEPrice(optionsChain, strike);
		trade.peEntryPrice = OptionsChainHelper.getPEPrice(optionsChain, strike);

		return trade;
	}

	private void closeTrade(Trade trade, int price, OptionsChain optionsChain) {
		trade.exit = price;
		if (trade.ceEntryPrice > 1) {
			trade.ceExitPrice = OptionsChainHelper.getCEPrice(optionsChain, trade.strike);
		}
		if (trade.peEntryPrice > 1) {
			trade.peExitPrice = OptionsChainHelper.getPEPrice(optionsChain, trade.strike);
		}
	}

	private List<Trade> doLongStrangle(int straddleStrike, int straddlePremimum, OptionsChain optionsChain) {
		Trade ceTrade = new Trade();
		Trade peTrade = new Trade();
		int upperStrike = (straddleStrike + straddlePremimum) / 100 * 100 + 100;
		int lowerStrike = (straddleStrike - straddlePremimum) / 100 * 100 - 100;

		ceTrade.entry = 0;
		ceTrade.isSellTrade = false;
		ceTrade.strike = upperStrike;
		ceTrade.ceEntryPrice = OptionsChainHelper.getCEPrice(optionsChain, upperStrike);

		peTrade.entry = 0;
		peTrade.isSellTrade = false;
		peTrade.strike = lowerStrike;
		peTrade.peEntryPrice = OptionsChainHelper.getPEPrice(optionsChain, lowerStrike);

		List<Trade> trades = new LinkedList<Trade>();
		trades.add(peTrade);
		trades.add(ceTrade);

		return trades;
	}

	public static void main(String args[]) {
		AlgoIronFly sswa = new AlgoIronFly();
		List<Trade> trades = sswa.execute("20-04-2022");
		int netProfit = 0;
		for (Trade trade : trades) {
			netProfit += trade.getNetPoints();
			System.out.println(trade);
		}
		System.out.println("Net profit is : " + 25 * netProfit);
	}
	
	@Override
	public String getName() {
		return "IronFly";
	}
}
