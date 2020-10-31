package StrategyAnalyzer;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
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
		List<Bar> expiryBars = getExpiryDayBars(series);
	}

	private static List<Bar> getExpiryDayBars(TimeSeries series) {
		List<Bar> expiryBars = new ArrayList<Bar>();
		return expiryBars;
	}

	public static void main(String args[]) throws FileNotFoundException {
		analyze(StockSymbols.BANKNIFTY.name, 29, 6);
	}
}
