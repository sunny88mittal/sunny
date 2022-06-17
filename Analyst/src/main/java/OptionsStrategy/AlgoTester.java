package OptionsStrategy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Constants.StockSymbols;

public class AlgoTester {

	private static final int BNF_LOT_SIZE = 25;

	private static String[] dates = new String[] { "10-06-2022", "13-06-2022", "14-06-2022", "15-06-2022", "16-06-2022", "17-06-2022" };

	public static void main(String args[]) {
		IOptionsStrategy strategy = new AlgoDataBasedOptionSelling();

		IOptionsStrategy strategy1 = new AlgoDataBasedOptionSellingWithTrailingSL(100, -1);
		IOptionsStrategy strategy2 = new AlgoDataBasedOptionSellingWithTrailingSL(75, -1);
		IOptionsStrategy strategy3 = new AlgoDataBasedOptionSellingWithTrailingSL(100, 100);

		IOptionsStrategy strategy4 = new AlgoDataBasedOptionSellingBothSidesWithTrailingSL(100, false, -1);
		IOptionsStrategy strategy5 = new AlgoDataBasedOptionSellingBothSidesWithTrailingSL(100, true, -1);
		IOptionsStrategy strategy6 = new AlgoDataBasedOptionSellingBothSidesWithTrailingSL(100, true, 100);

		IOptionsStrategy strategy7 = new AlgoDataBasedOptionSellingBothSidesWithTrailingSL(75, false, -1);
		IOptionsStrategy strategy8 = new AlgoDataBasedOptionSellingBothSidesWithTrailingSL(75, true, -1);

		IOptionsStrategy strategy9 = new AlgoDataBasedOptionSpreads(0, 100, 100, 0);
		IOptionsStrategy strategy10 = new AlgoDataBasedOptionSpreads(1000, 100, 100, 0);
		IOptionsStrategy strategy11 = new AlgoDataBasedOptionSpreads(500, 100, 100, 0);

		IOptionsStrategy strategy12 = new AlgoDataBasedOptionSpreads(0, 100, 100, 400);
		IOptionsStrategy strategy13 = new AlgoDataBasedOptionSpreads(1000, 100, 100, 400);
		IOptionsStrategy strategy14 = new AlgoDataBasedOptionSpreads(500, 100, 100, 400);

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
		optionsStratgeies.add(strategy9);
		optionsStratgeies.add(strategy10);
		optionsStratgeies.add(strategy11);
		optionsStratgeies.add(strategy12);
		optionsStratgeies.add(strategy13);
		optionsStratgeies.add(strategy14);

		Map<String, List<List<Trade>>> stratgeyTradeMap = getTrades(dates, optionsStratgeies);
		printProfits(stratgeyTradeMap);
	}

	private static Map<String, List<List<Trade>>> getTrades(String[] dates, List<IOptionsStrategy> optionsStrategies) {
		Map<String, List<List<Trade>>> stratgeyTradesMap = new LinkedHashMap<String, List<List<Trade>>>();
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
