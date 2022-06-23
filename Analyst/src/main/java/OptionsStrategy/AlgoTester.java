package OptionsStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Constants.FileConstants;
import Constants.StockSymbols;

public class AlgoTester {

	private static final int BNF_LOT_SIZE = 25;

	private static String[] dates = new String[] { "23-06-2022" };

	public static void main(String args[]) {
		// dates = getAllDates();

		IOptionsStrategy strategy = new AlgoDataBasedOptionSelling(100, -1, true);
		IOptionsStrategy strategy1 = new AlgoDataBasedOptionSelling(100, -1, false);
		IOptionsStrategy strategy2 = new AlgoDataBasedOptionSelling(100, 100, false);

		IOptionsStrategy strategy3 = new AlgoDataBasedFutureTrading(200, -1, false);
		IOptionsStrategy strategy4 = new AlgoDataBasedFutureTrading(200, 200, false);

		IOptionsStrategy strategy5 = new AlgoDataBasedOptionSellingBothSides(100, false, -1);
		IOptionsStrategy strategy6 = new AlgoDataBasedOptionSellingBothSides(100, true, -1);
		IOptionsStrategy strategy7 = new AlgoDataBasedOptionSellingBothSides(100, true, 100);

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

		IOptionsStrategy strategy22 = new AlgoDataBasedOptionSpreads(0, 100, 100, 400, true);
		IOptionsStrategy strategy23 = new AlgoDataBasedOptionSpreads(1000, 100, 100, 400, true);
		IOptionsStrategy strategy24 = new AlgoDataBasedOptionSpreads(500, 100, 100, 400, true);

		List<IOptionsStrategy> optionsStratgeies = new ArrayList<IOptionsStrategy>();
		optionsStratgeies.add(strategy);
		optionsStratgeies.add(strategy1);
		optionsStratgeies.add(strategy2);
		optionsStratgeies.add(strategy3);
		optionsStratgeies.add(strategy4);
		optionsStratgeies.add(strategy5);
		optionsStratgeies.add(strategy6);
		optionsStratgeies.add(strategy7);
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
		optionsStratgeies.add(strategy22);
		optionsStratgeies.add(strategy23);
		optionsStratgeies.add(strategy24);

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

	private static String[] getAllDates() {
		String[] dates = null;
		;
		String dirLocation = FileConstants.OPTIONS_FILE_BASE_PATH;
		File file = new File(dirLocation);
		File[] files = file.listFiles();

		Arrays.sort(files, new Comparator<File>() {
			public int compare(File f1, File f2) {
				return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
			}
		});

		dates = new String[files.length];
		int i = 0;
		for (File file1 : files) {
			dates[i] = file1.getName();
			++i;
		}

		return dates;
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
