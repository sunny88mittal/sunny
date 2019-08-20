package Analyzer;

import java.time.LocalDateTime;
import java.util.List;

import Entities.FNOData;

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
			if (fnoDataEntry.INSTRUMENT.startsWith(CONST_OPT) && fnoDataEntry.SYMBOL.equals(stockSymbol)
					&& fnoDataEntry.EXPIRY_DT.toUpperCase().contains(getCurrentMonth())) {
				if (fnoDataEntry.OPTION_TYP.equals(CONST_OPT_CE)) {
					callOI += fnoDataEntry.OPEN_INT;
				} else if (fnoDataEntry.OPTION_TYP.equals(CONST_OPT_PE)) {
					putOI += fnoDataEntry.OPEN_INT;
				}
			}
		}

		return (putOI / callOI);
	}

	private static String getCurrentMonth() {
		LocalDateTime date = LocalDateTime.now();
		return date.getMonth().toString().subSequence(0, 3).toString().toUpperCase();
	}
}
