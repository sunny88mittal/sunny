package Analyzer;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
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

	public static float getMaxPain(List<FNOData> fnoData, String stockSymbol) {
		// Get CE, PE Entries for current expiry
		String expiry = null;
		List<FNOData> ceEntries = new ArrayList<FNOData>();
		List<FNOData> peEntries = new ArrayList<FNOData>();
		for (FNOData fnoDataEntry : fnoData) {
			if (fnoDataEntry.INSTRUMENT.startsWith(CONST_OPT) && fnoDataEntry.SYMBOL.equals(stockSymbol)) {
				if (expiry == null) {
					expiry = fnoDataEntry.EXPIRY_DT;
				} else if (!fnoDataEntry.EXPIRY_DT.equals(expiry)) {
					continue;
				}

				if (fnoDataEntry.OPTION_TYP.equals(CONST_OPT_CE)) {
					ceEntries.add(fnoDataEntry);
				} else if (fnoDataEntry.OPTION_TYP.equals(CONST_OPT_PE)) {
					peEntries.add(fnoDataEntry);
				}
			}
		}

		// Compute max pain
		float maxPainAt = Float.MAX_VALUE;
		float maxPain = Float.MAX_VALUE;
		for (int i = 0; i < ceEntries.size(); i++) {
			float spotPrice = Float.parseFloat(ceEntries.get(i).STRIKE_PR);
			float totalPain = 0.0f;

			// Call Pain
			for (FNOData fnoDataEntry : ceEntries) {
				float strikePrice = Float.parseFloat(fnoDataEntry.STRIKE_PR);
				float openInterest = fnoDataEntry.OPEN_INT;
				float pain = openInterest * (spotPrice - strikePrice);
				if (pain > 0) {
					totalPain += pain;
				}
			}

			// Put Pain
			for (FNOData fnoDataEntry : peEntries) {
				float strikePrice = Float.parseFloat(fnoDataEntry.STRIKE_PR);
				float openInterest = fnoDataEntry.OPEN_INT;
				float pain = openInterest * (strikePrice - spotPrice);
				if (pain > 0) {
					totalPain += pain;
				}
			}

			if (totalPain < maxPain) {
				maxPain = totalPain;
				maxPainAt = spotPrice;
			}
		}

		return maxPainAt;
	}

	public static void main(String args[]) throws FileNotFoundException {
		String stockSymbol = StockSymbols.MARUTI.name;
		LocalDate date = LocalDate.of(2019, 8, 26);
		List<String> rawData = FileReader.getFNOData(date);
		List<FNOData> fnoData = DataUtil.getFNOData(rawData);
		System.out.println(getMaxPain(fnoData, stockSymbol));
	}
}
