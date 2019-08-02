package Constants;

import java.util.ArrayList;
import java.util.List;

public enum StockSymbols {

	NIFTY("NIFTY", "256265"), 
	
	BANK_NIFTY("BANKNIFTY", "260105"),
	
	BAJAJ_FINANCE("BAJFINANCE", "81153"), 
	
	SRIRAM_TRANSPORTATION_FINANCE("SRTRANSFIN", "1102337"),

	MARUTI_SUZUKI("MARUTI", ""),

	RELIANCE("RELIANCE", "738561"),

	HDFC("HDFC", "340481"),

	HDFC_BANK("HDFCBANK", "341249"),

	KOTAK_BANK("KOTAKBANK", "492033"),

	INDUSIND_BANK("INDUSINDBK", "1346049"),

	AXIS_BANK("AXISBANK", "1510401"),

	ICICI_BANK("ICICIBANK", "1270529"),

	YES_BANK("YESBANK", "3050241"),

	RBL_BANK("RBLBANK", "4708097"),

	SBI_BANK("SBIN", "779521");

	public String name;

	public String code;

	private StockSymbols(String name, String code) {
		this.name = name;
		this.code = code;
	}
	
	public static List<StockSymbols> getAllStocksList() {
		List<StockSymbols> stockSymbolsList = new ArrayList<StockSymbols>();
		stockSymbolsList.add(NIFTY);
		stockSymbolsList.add(BANK_NIFTY);
		stockSymbolsList.add(BAJAJ_FINANCE);
		stockSymbolsList.add(SRIRAM_TRANSPORTATION_FINANCE);
		stockSymbolsList.add(MARUTI_SUZUKI);
		stockSymbolsList.add(RELIANCE);
		stockSymbolsList.add(HDFC);
		stockSymbolsList.add(HDFC_BANK);
		stockSymbolsList.add(INDUSIND_BANK);
		stockSymbolsList.add(AXIS_BANK);
		stockSymbolsList.add(ICICI_BANK);
		stockSymbolsList.add(YES_BANK);
		stockSymbolsList.add(RBL_BANK);
		stockSymbolsList.add(SBI_BANK);
		return stockSymbolsList;
	}
}
