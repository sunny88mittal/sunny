package Zerodha.Trader.Core;

public class TradingSymbolHelper {

	public static String getFullOptionSymbol(String index, String dateVal, int strike, String type) {
		return index + dateVal + strike + type;
	}
}
