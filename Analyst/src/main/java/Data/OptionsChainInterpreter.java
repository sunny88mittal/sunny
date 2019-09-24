package Data;

import Constants.TradeConstants;
import Entities.OptionsChain;
import Entities.OptionsDataRow;

public class OptionsChainInterpreter {

	public static void interpretOptionsChain(OptionsChain current) {
		// Call Options Interpretation
		for (OptionsDataRow currentOptionsDataRow : current.callOptions) {
			if (currentOptionsDataRow.LTP != 0 && currentOptionsDataRow.openInterest != 0) {
				String callInterpretation = getOptionInterpretation(currentOptionsDataRow.netChange,
						currentOptionsDataRow.openInterestChange);
				String trend = getCallTrend(callInterpretation);
				currentOptionsDataRow.interpretation = callInterpretation;
				currentOptionsDataRow.trend = trend;
			}
		}

		// Put Options Interpretation
		for (OptionsDataRow currentOptionsDataRow : current.putOptions) {
			if (currentOptionsDataRow.LTP != 0 && currentOptionsDataRow.openInterest != 0) {
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
}
