package StrategyAnalyzer;

import java.io.FileNotFoundException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;

import Constants.CandleStickInterval;
import Constants.StockSymbols;
import DataUtil.DataUtil;

public class NormalDistributionStrategyAnalyzer {

	private static void analyze(String instrument, int noOfWeeks, float percentage) throws FileNotFoundException {
		TimeSeries series = DataUtil.getTimeSeries(instrument, CandleStickInterval.DAY);
		List<Bar> expiryBars = getExpiryDayBars(series);
		for (int i = 0; i < expiryBars.size() - 1; i++) {
			Bar endBar = expiryBars.get(i);
			Bar startBar = expiryBars.get(i + 1);
			float percentageChange = 100 * (endBar.getClosePrice().floatValue() - startBar.getClosePrice().floatValue())
					/ startBar.getClosePrice().floatValue();
			System.out.println(startBar.getSimpleDateName() + "," + percentageChange);
		}
	}

	private static List<Bar> getExpiryDayBars(TimeSeries series) {
		List<Bar> expiryBars = new ArrayList<Bar>();

		ZonedDateTime todaysDate = ZonedDateTime.now();
		todaysDate = todaysDate.truncatedTo(ChronoUnit.DAYS);
		ZonedDateTime latestMonday = todaysDate.minusDays(todaysDate.getDayOfWeek().getValue() - 1);
		ZonedDateTime latestFriday = latestMonday.plusDays(4);

		while (latestMonday.isAfter(series.getBar(0).getEndTime())) {
			Bar latestExpiry = null;
			for (Bar bar : series.getBarData()) {
				boolean isCurrentWeeksBar = bar.getEndTime().isEqual(latestMonday)
						|| (bar.getEndTime().isAfter(latestMonday) && bar.getEndTime().isBefore(latestFriday));
				if (isCurrentWeeksBar) {
					if (latestExpiry == null || latestExpiry.getEndTime().isBefore(bar.getEndTime())) {
						latestExpiry = bar;
					}
				}
			}

			if (latestExpiry == null) {
				break;
			}

			expiryBars.add(latestExpiry);
			latestMonday = latestMonday.minusDays(7);
			latestFriday = latestFriday.minusDays(7);
		}

		return expiryBars;
	}

	public static void main(String args[]) throws FileNotFoundException {
		analyze(StockSymbols.BANKNIFTY.name, 29, 6);
	}
}
