package StrategyAnalyzer;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;

import Constants.CandleStickInterval;
import Constants.StockSymbols;
import DataProvider.FNODataProvider;
import DataUtil.DataUtil;
import Entities.FNOData;

public class NormalDistributionStrategyAnalyzer {

	private static void analyze(String instrument, int noOfWeeks, float percentage) throws FileNotFoundException {
		TimeSeries series = DataUtil.getTimeSeries(instrument, CandleStickInterval.DAY);
		List<FNOData> fnoData = FNODataProvider.getFNOData("OPTIDX", instrument, LocalDate.now().minusDays(2));
		for (int i = 0; i < noOfWeeks; i++) {
			getLastWeeksStratingBar(series, i);
		}
	}

	private static Bar getLastWeeksStratingBar(TimeSeries series, int weekOffset) {
		return null;
	}

	private Bar getLastWeeksEndingBar(TimeSeries series, int weekOffset) {
		return null;
	}

	public static void main(String args[]) throws FileNotFoundException {
		analyze(StockSymbols.BANKNIFTY.name, 29, 6);
	}
}
