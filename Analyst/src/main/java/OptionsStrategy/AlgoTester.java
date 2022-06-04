package OptionsStrategy;

import java.util.ArrayList;
import java.util.List;

public class AlgoTester {

	private static final int BNF_LOT_SIZE = 25;

	private static String[] dates = new String[] { "19-04-2022","20-04-2022", "21-04-2022", "22-04-2022", "25-04-2022",
			"29-04-2022", "02-06-2022", "03-06-2022" };

	public static void main(String args[]) {
		IOptionsStrategy strategy = new AlgoDataBasedOptionSellingWithSL(75);
		List<Integer> strategyProfits = getProfits(dates, strategy);
		System.out.println("AlgoDataBasedOptionSelling :" + strategyProfits);
	}

	private static List<Integer> getProfits(String[] dates, IOptionsStrategy optionsStrategy) {
		List<Integer> profits = new ArrayList<Integer>();
		for (String date : dates) {
			System.out.println("Running for date :" + date + " Strategy :" + optionsStrategy.getClass().getName());
			List<Trade> trades = optionsStrategy.execute(date);
			int netProfit = 0;
			for (Trade trade : trades) {
				netProfit += trade.getNetPoints();
			}
			profits.add(BNF_LOT_SIZE * netProfit);
			printProfits(trades);
		}

		return profits;
	}

	private static void printProfits(List<Trade> trades) {
		int netProfit = 0;
		for (Trade trade : trades) {
			netProfit += trade.getNetPoints();
			System.out.println(trade);
		}
		System.out.println("Net profit is : " + BNF_LOT_SIZE * netProfit);
	}
}
