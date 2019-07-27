package Statistics;

public class Statistics {

	public int totalTrades = 0;
	public int profitableTrades = 0;
	public int loosingtrades = 0;
	public float grossProfit = 0;
	public float onlyProft = 0;
	public float onlyLoss = 0;
	public float charges = 0;

	public void printStats() {
		System.out.println("Total no. of trades : " + totalTrades);
		System.out.println("Profitable trades : " + profitableTrades);
		System.out.println("Loosing trades : " + loosingtrades);
		System.out.println("From Profitable Trades : " + onlyProft);
		System.out.println("From Loosing Trades : " + onlyLoss);
		System.out.println("Gross profit : " + grossProfit);
		System.out.println("Charges : " + charges);
		System.out.println("Net Profit : " + (grossProfit - charges));
	}
}
