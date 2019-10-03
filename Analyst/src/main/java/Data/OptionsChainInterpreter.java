package Data;

import java.util.List;

import Constants.TradeConstants;
import Entities.OptionsChain;
import Entities.OptionsDataRow;

public class OptionsChainInterpreter {

	public static void interpretOptionsChain(OptionsChain current) {
		// Call Options Interpretation
		for (OptionsDataRow currentOptionsDataRow : current.callOptions) {
			if (currentOptionsDataRow.netChange != 0 && currentOptionsDataRow.openInterestChange != 0) {
				String callInterpretation = getOptionInterpretation(currentOptionsDataRow.netChange,
						currentOptionsDataRow.openInterestChange);
				String trend = getCallTrend(callInterpretation);
				currentOptionsDataRow.interpretation = callInterpretation;
				currentOptionsDataRow.trend = trend;
			}
		}

		// Put Options Interpretation
		for (OptionsDataRow currentOptionsDataRow : current.putOptions) {
			if (currentOptionsDataRow.netChange != 0 && currentOptionsDataRow.openInterestChange != 0) {
				String putInterpretation = getOptionInterpretation(currentOptionsDataRow.netChange,
						currentOptionsDataRow.openInterestChange);
				String trend = getPutTrend(putInterpretation);
				currentOptionsDataRow.interpretation = putInterpretation;
				currentOptionsDataRow.trend = trend;
			}
		}
	}

	private static String getPutTrend(String optionInterpretation) {
		String trend = TradeConstants.BEARISH;
		if (optionInterpretation.equals(TradeConstants.LONG_UNWINDING)
				|| optionInterpretation.equals(TradeConstants.SHORT_BUILDUP)) {
			trend = TradeConstants.BULLISH;
		}
		return trend;
	}

	private static String getCallTrend(String optionInterpretation) {
		String trend = TradeConstants.BULLISH;
		if (optionInterpretation.equals(TradeConstants.LONG_UNWINDING)
				|| optionInterpretation.equals(TradeConstants.SHORT_BUILDUP)) {
			trend = TradeConstants.BEARISH;
		}
		return trend;
	}

	private static String getOptionInterpretation(double priceChange, double oiChange) {
		String optionInterpretation = "";

		if (priceChange <= 0 && oiChange <= 0) {
			optionInterpretation = TradeConstants.LONG_UNWINDING;
		} else if (priceChange < 0 && oiChange > 0) {
			optionInterpretation = TradeConstants.SHORT_BUILDUP;
		} else if (priceChange > 0 && oiChange < 0) {
			optionInterpretation = TradeConstants.SHORT_UNWINDING;
		} else if (priceChange > 0 && oiChange > 0) {
			optionInterpretation = TradeConstants.LONG_BUILDUP;
		}

		return optionInterpretation;
	}

	public static double getMaxPain(OptionsChain current) {
		// Compute max pain
		double maxPainAt = Double.MAX_VALUE;
		double maxPain = Double.MAX_VALUE;
		List<OptionsDataRow> ceEntries = current.callOptions;
		List<OptionsDataRow> peEntries = current.putOptions;

		for (int i = 0; i < ceEntries.size(); i++) {
			double spotPrice = ceEntries.get(i).strikePrice;
			double totalPain = 0.0;

			// Call Pain
			for (OptionsDataRow ceEntry : ceEntries) {
				double strikePrice = ceEntry.strikePrice;
				double openInterest = ceEntry.openInterest;
				double pain = openInterest * (spotPrice - strikePrice);
				if (pain > 0) {
					totalPain += pain;
				}
			}

			// Put Pain
			for (OptionsDataRow peEntry : peEntries) {
				double strikePrice = peEntry.strikePrice;
				double openInterest = peEntry.openInterest;
				double pain = openInterest * (strikePrice - spotPrice);
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
}
