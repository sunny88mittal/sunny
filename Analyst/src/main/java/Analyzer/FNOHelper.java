package Analyzer;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

import Constants.StockSymbols;
import DataUtil.DataUtil;
import Entities.FNOData;
import File.FileReader;

public class FNOHelper {

	private static String CONST_FUT = "FUT";

	private static String CONST_OPT = "OPT";

	private static String CONST_OPT_PE = "PE";

	private static String CONST_OPT_CE = "CE";

	public static long getChangeInOI(List<FNOData> fnoData, String stockSymbol) {
		long changeInOI = 0;
		for (FNOData fnoDataEntry : fnoData) {
			if (fnoDataEntry.INSTRUMENT.startsWith(CONST_FUT) && fnoDataEntry.SYMBOL.equals(stockSymbol)) {
				changeInOI += fnoDataEntry.CHG_IN_OI;
			}
		}
		return changeInOI;
	}

	public static float getPCR(List<FNOData> fnoData, String stockSymbol) {
		float callOI = 0;
		float putOI = 0;
		for (FNOData fnoDataEntry : fnoData) {
			if (fnoDataEntry.INSTRUMENT.startsWith(CONST_OPT) && fnoDataEntry.SYMBOL.equals(stockSymbol)) {
				if (fnoDataEntry.OPTION_TYP.equals(CONST_OPT_CE)) {
					callOI += fnoDataEntry.OPEN_INT;
				} else if (fnoDataEntry.OPTION_TYP.equals(CONST_OPT_PE)) {
					putOI += fnoDataEntry.OPEN_INT;
				}
			}
		}

		return (putOI / callOI);
	}

	public static void main(String args[]) throws FileNotFoundException {
		String stockSymbol = StockSymbols.TCS.name;
		LocalDate date = LocalDate.of(2019, 07, 04);
		List<String> rawData = FileReader.getFNOData(date);
		List<FNOData> fnoData = DataUtil.getFNOData(rawData);
		System.out.println(getPCR(fnoData, stockSymbol));
	}
}
