package OptionsStrategy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Constants.StockSymbols;

public class AlgoTester {

	private static final int BNF_LOT_SIZE = 25;

	private static String[] dates = new String[] { "19-04-2022", "20-04-2022", "21-04-2022", "22-04-2022", "25-04-2022",
			"29-04-2022", "02-06-2022", "03-06-2022", "07-06-2022", "08-06-2022", "10-06-2022", "13-06-2022",
			"14-06-2022", "15-06-2022", "16-06-2022", "17-06-2022" };

	public static void main(String args[]) {
		IOptionsStrategy strategy = new AlgoDataBasedOptionSelling(100, -1, true);
		IOptionsStrategy strategy1 = new AlgoDataBasedOptionSelling(100, -1, false);
		IOptionsStrategy strategy2 = new AlgoDataBasedOptionSelling(75, -1, false);
		IOptionsStrategy strategy3 = new AlgoDataBasedOptionSelling(100, 100, false);
		IOptionsStrategy strategy4 = new AlgoDataBasedOptionSelling(200, -1, false, -1000);

		IOptionsStrategy strategy5 = new AlgoDataBasedOptionSellingBothSides(100, false, -1);
		IOptionsStrategy strategy6 = new AlgoDataBasedOptionSellingBothSides(100, true, -1);
		IOptionsStrategy strategy7 = new AlgoDataBasedOptionSellingBothSides(100, true, 100);

		IOptionsStrategy strategy8 = new AlgoDataBasedOptionSellingBothSides(75, false, -1);
		IOptionsStrategy strategy9 = new AlgoDataBasedOptionSellingBothSides(75, true, -1);

		IOptionsStrategy strategy10 = new AlgoDataBasedOptionSpreads(0, 100, 0, 0);
		IOptionsStrategy strategy11 = new AlgoDataBasedOptionSpreads(1000, 100, 0, 0);
		IOptionsStrategy strategy12 = new AlgoDataBasedOptionSpreads(500, 100, 0, 0);

		IOptionsStrategy strategy13 = new AlgoDataBasedOptionSpreads(0, 100, 0, 400);
		IOptionsStrategy strategy14 = new AlgoDataBasedOptionSpreads(1000, 100, 0, 400);
		IOptionsStrategy strategy15 = new AlgoDataBasedOptionSpreads(500, 100, 0, 400);

		IOptionsStrategy strategy16 = new AlgoDataBasedOptionSpreads(0, 100, 100, 0);
		IOptionsStrategy strategy17 = new AlgoDataBasedOptionSpreads(1000, 100, 100, 0);
		IOptionsStrategy strategy18 = new AlgoDataBasedOptionSpreads(500, 100, 100, 0);

		IOptionsStrategy strategy19 = new AlgoDataBasedOptionSpreads(0, 100, 100, 400);
		IOptionsStrategy strategy20 = new AlgoDataBasedOptionSpreads(1000, 100, 100, 400);
		IOptionsStrategy strategy21 = new AlgoDataBasedOptionSpreads(500, 100, 100, 400);

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
		optionsStratgeies.add(strategy15);
		optionsStratgeies.add(strategy16);
		optionsStratgeies.add(strategy17);
		optionsStratgeies.add(strategy18);
		optionsStratgeies.add(strategy19);
		optionsStratgeies.add(strategy20);
		optionsStratgeies.add(strategy21);

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
