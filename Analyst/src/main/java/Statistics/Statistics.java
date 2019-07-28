package Statistics;

import java.util.LinkedHashMap;
import java.util.Map;

public class Statistics {

	public int totalTrades = 0;
	public int profitableTrades = 0;
	public int loosingtrades = 0;
	public float grossProfit = 0;
	public float onlyProft = 0;
	public float onlyLoss = 0;
	public float charges = 0;

	public static final String TOTAL_TRADES = "Total Trades";

	public static final String PROFITABLE_TRADES = "Profitable Trades";

	public static final String PROFITABLE_TRADES_PERCENTAGE = "Profitable Trades %age";

	public static final String LOOSING_TRADES = "Loosing Trades";

	public static final String LOOSING_TRADES_PERCENTAGE = "Loosing Trades %age";

	public static final String FROM_PROFITABLE_TRADES = "From Profitable Trades";

	public static final String FROM_LOOSING_TRADES = "From Loosing Trades";

	public static final String GROSS_PROFIT = "Gross Profit";

	public static final String CHARGES = "Charges";

	public static final String NET_PROFIT = "Net Profit";

	public void printStats() {
		System.out.println("Total no. of trades : " + totalTrades);
		System.out.println("Profitable trades : " + profitableTrades);
		System.out.println("Profitable trades %age : " + ((profitableTrades * 1.0) / (totalTrades * 1.0)) * 100);
		System.out.println("Loosing trades : " + loosingtrades);
		System.out.println("Loosing trades %age : " + ((loosingtrades * 1.0) / (totalTrades * 1.0)) * 100);
		System.out.println("From Profitable Trades : " + onlyProft);
		System.out.println("From Loosing Trades : " + onlyLoss);
		System.out.println("Gross profit : " + grossProfit);
		System.out.println("Charges : " + charges);
		System.out.println("Net Profit : " + (grossProfit - charges));
	}

	public Map<String, String> getStatsAsMap() {
		Map<String, String> statsAsMap = new LinkedHashMap<String, String>();
		statsAsMap.put(TOTAL_TRADES, totalTrades + "");
		statsAsMap.put(PROFITABLE_TRADES, profitableTrades + "");
		statsAsMap.put(PROFITABLE_TRADES_PERCENTAGE, ((profitableTrades * 1.0) / (totalTrades * 1.0)) * 100 + "");
		statsAsMap.put(LOOSING_TRADES, loosingtrades + "");
		statsAsMap.put(LOOSING_TRADES_PERCENTAGE, ((loosingtrades * 1.0) / (totalTrades * 1.0)) * 100 + "");
		statsAsMap.put(FROM_PROFITABLE_TRADES, onlyProft + "");
		statsAsMap.put(FROM_LOOSING_TRADES, onlyLoss + "");
		statsAsMap.put(GROSS_PROFIT, grossProfit + "");
		statsAsMap.put(CHARGES, charges + "");
		statsAsMap.put(NET_PROFIT, (grossProfit - charges) + "");
		return statsAsMap;
	}
}
