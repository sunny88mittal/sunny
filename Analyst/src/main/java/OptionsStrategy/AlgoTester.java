package OptionsStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Constants.StockSymbols;

public class AlgoTester {

	private static final int BNF_LOT_SIZE = 25;

	private static String[] dates = new String[] { "19-04-2022", "20-04-2022", "21-04-2022", "22-04-2022", "25-04-2022",
			"29-04-2022", "02-06-2022", "03-06-2022" };

	public static void main(String args[]) {
		IOptionsStrategy strategy = new AlgoDataBasedOptionSelling();
		IOptionsStrategy strategy1 = new AlgoDataBasedOptionSellingWithSL(100);
		IOptionsStrategy strategy2 = new AlgoDataBasedOptionSellingWithSL(75);
		IOptionsStrategy strategy3 = new AlgoDataBasedOptionSellingBothSides(100, true);
		IOptionsStrategy strategy4 = new AlgoDataBasedOptionSellingBothSides(75, true);
		IOptionsStrategy strategy5 = new AlgoDataBasedOptionSellingBothSides(100, false);
		IOptionsStrategy strategy6 = new AlgoDataBasedOptionSellingBothSides(75, false);
		IOptionsStrategy strategy7 = new AlgoDataBasedOptionSellingBothSidesWithTrailingSL(100, true, 100);
		IOptionsStrategy strategy8 = new AlgoDataBasedOptionSellingWithTrailingSL(100, 100);
		List<IOptionsStrategy> optionsStratgeies = new ArrayList<IOptionsStrategy>();
		optionsStratgeies.add(strategy);
		optionsStratgeies.add(strategy1);
		optionsStratgeies.add(strategy2);
		optionsStratgeies.add(strategy3);
		optionsStratgeies.add(strategy4);
		optionsStratgeies.add(strategy5);
		optionsStratgeies.add(strategy6);
		optionsStratgeies.add(strategy7);
		optionsStratgeies.add(strategy8);

		Map<String, List<List<Trade>>> stratgeyTradeMap = getTrades(dates, optionsStratgeies);
		printProfits(stratgeyTradeMap);
	}

	private static Map<String, List<List<Trade>>> getTrades(String[] dates, List<IOptionsStrategy> optionsStrategies) {
		Map<String, List<List<Trade>>> stratgeyTradesMap = new HashMap<String, List<List<Trade>>>();
		for (String date : dates) {
			long startTime = System.currentTimeMillis();
			OptionsDataProvider.loadCache(StockSymbols.BANKNIFTY.name, date);
			long endTime = System.currentTimeMillis();
			long timeTaken = (endTime - startTime) / 1000;
			System.out.println("Time taken to load cache : " + timeTaken);

			for (IOptionsStrategy optionsStrategy : optionsStrategies) {
				System.out.println("Running for date :" + date + " Strategy :" + optionsStrategy.getName());
				List<Trade> trades = optionsStrategy.execute(date);
				List<List<Trade>> strategyTradesList = stratgeyTradesMap.get(optionsStrategy.getName());
				if (strategyTradesList == null) {
					strategyTradesList = new ArrayList<List<Trade>>();
				}
				strategyTradesList.add(trades);
				stratgeyTradesMap.put(optionsStrategy.getName(), strategyTradesList);
			}
		}

		return stratgeyTradesMap;
	}

	private static void printProfits(Map<String, List<List<Trade>>> stratgeyTradeMap) {
		for (Entry<String, List<List<Trade>>> entry : stratgeyTradeMap.entrySet()) {
			String stratgeyName = entry.getKey();
			List<Integer> profits = new ArrayList<Integer>();
			for (List<Trade> trades : entry.getValue()) {
				int profit = 0;
				for (Trade trade : trades) {
					profit += trade.getNetPoints();
				}
				profits.add(profit * BNF_LOT_SIZE);
			}
			System.out.println(stratgeyName + " Profits : " + profits);
		}
	}
}
