package Constants;

import java.util.ArrayList;
import java.util.List;

public enum StockSymbols {

	NIFTY("NIFTY", "256265"), 
	
	BANKNIFTY("BANKNIFTY", "260105"),
	
	BAJFINANCE("BAJFINANCE", "81153"), 
	
	SRTRANSFIN("SRTRANSFIN", "1102337"),

	MARUTI("MARUTI", "2815745"),

	RELIANCE("RELIANCE", "738561"),

	HDFC("HDFC", "340481"),

	HDFCBANK("HDFCBANK", "341249"),

	KOTAKBANK("KOTAKBANK", "492033"),

	INDUSINDBK("INDUSINDBK", "1346049"),

	AXISBANK("AXISBANK", "1510401"),

	ICICIBANK("ICICIBANK", "1270529"),

	YESBANK("YESBANK", "3050241"),

	RBLBANK("RBLBANK", "4708097"),

	SBIN("SBIN", "779521");

	public String name;

	public String code;

	private StockSymbols(String name, String code) {
		this.name = name;
		this.code = code;
	}
	
	public static List<StockSymbols> getAllStocksList() {
		List<StockSymbols> stockSymbolsList = new ArrayList<StockSymbols>();
		stockSymbolsList.add(NIFTY);
		stockSymbolsList.add(BANKNIFTY);
		stockSymbolsList.add(BAJFINANCE);
		stockSymbolsList.add(SRTRANSFIN);
		stockSymbolsList.add(MARUTI);
		stockSymbolsList.add(RELIANCE);
		stockSymbolsList.add(HDFC);
		stockSymbolsList.add(HDFCBANK);
		stockSymbolsList.add(INDUSINDBK);
		stockSymbolsList.add(AXISBANK);
		stockSymbolsList.add(ICICIBANK);
		stockSymbolsList.add(YESBANK);
		stockSymbolsList.add(RBLBANK);
		stockSymbolsList.add(SBIN);
		return stockSymbolsList;
	}
}
