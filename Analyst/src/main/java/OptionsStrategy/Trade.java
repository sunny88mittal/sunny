package OptionsStrategy;

public class Trade {

	public double strike;

	public double entry;

	public double exit;

	public double ceEntryPrice;

	public double ceExitPrice;

	public double ceStopLoss;

	public double peEntryPrice;

	public double peExitPrice;

	public double peStopLoss;

	public boolean isSellTrade = true;

	public double getNetPoints() {
		int multiplier = isSellTrade == true ? 1 : -1;
		return multiplier * (ceEntryPrice - ceExitPrice + peEntryPrice - peExitPrice);
	}

	public String toString() {
		String tradeString = "";
		tradeString += "Entry : " + entry + "\r\n" + "Exit : " + exit + "\r\n" + "Strike : " + strike + "\r\n"
				+ "CEEntryPrice : " + ceEntryPrice + "\r\n" + "CEExitPrice : " + ceExitPrice + "\r\n"
				+ "PEEntryPrice : " + peEntryPrice + "\r\n" + "PEEexitPrice : " + peExitPrice + "\r\n" + "Net Points : "
				+ getNetPoints() + "\r\n";
		return tradeString;
	}
}
