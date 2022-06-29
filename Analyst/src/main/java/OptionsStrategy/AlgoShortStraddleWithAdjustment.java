package OptionsStrategy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Constants.StockSymbols;
import Entities.OptionsChain;

public class AlgoShortStraddleWithAdjustment implements IOptionsStrategy {

	@Override
	public List<Trade> execute(String date) {
		List<Trade> trades = new ArrayList<Trade>();
		boolean isTradeOpen = false;
		Trade trade = null;

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
				trade = doShortStraddle(price, optionsChain);
				isTradeOpen = true;
				trades.add(trade);
			}

			// Adjustment trade
			if (trade != null && (price >= 1.01 * trade.entry || price <= .99 * trade.entry)) {
				closeShortStraddle(trade, price, optionsChain);
				trade = doShortStraddle(price, optionsChain);
				trades.add(trade);
			}

			// Closing the trade at market close
			if (trade != null && ((hours >= 15 && minutes >= 29) || hours >= 16)) {
				closeShortStraddle(trade, price, optionsChain);
				break;
			}
		}

		return trades;
	}

	private Trade doShortStraddle(int price, OptionsChain optionsChain) {
		System.out.println("Initiating Starddle at :" + price);
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

	private void closeShortStraddle(Trade trade, int price, OptionsChain optionsChain) {
		trade.exit = price;
		trade.ceExitPrice = OptionsChainHelper.getCEPrice(optionsChain, trade.strike);
		trade.peExitPrice = OptionsChainHelper.getPEPrice(optionsChain, trade.strike);
	}

	public static void main(String args[]) {
		AlgoShortStraddleWithAdjustment sswa = new AlgoShortStraddleWithAdjustment();
		List<Trade> trades = sswa.execute("19-04-2022");
		int netProfit = 0;
		for (Trade trade : trades) {
			netProfit += trade.getNetPoints();
			System.out.println(trade);
		}
		System.out.println("Net profit is : " + 25 * netProfit);
	}

	@Override
	public String getName() {
		return "ShortStraddleWithAdjustment";
	}
}
