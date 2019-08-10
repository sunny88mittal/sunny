package Statistics;

import java.text.DecimalFormat;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;

import Constants.CandleStickInterval;
import Constants.StockSymbols;
import DataUtil.DataUtil;

public class StatisticsUtil {

	private static DecimalFormat df = new DecimalFormat("#.##");

	public static double getVolatilityLastNIntervals(TimeSeries series, int intervals) {
		double[] dailyReturn = new double[intervals];

		// Prepare daily returns
		int seriesLength = series.getBarCount();
		for (int i = 0; i < intervals; i++) {
			Bar previousar = series.getBar(seriesLength - intervals - (i - 1));
			Bar currentBar = series.getBar(seriesLength - intervals - (i));

			double previousClose = previousar.getClosePrice().doubleValue();
			double currentClose = currentBar.getClosePrice().doubleValue();

			dailyReturn[i] = (currentClose - previousClose) / previousClose;
		}

		// Get Volatility
		DescriptiveStatistics descStats = new DescriptiveStatistics(dailyReturn);
		double standardDeviation = descStats.getStandardDeviation() * 100;
		return (Double.parseDouble(df.format(standardDeviation)));
	}

	public static double getReturnsLastNIntervals(TimeSeries series, int intervals) {
		Bar currentBar = series.getLastBar();
		Bar startBar = series.getBar(series.getBarCount() - intervals);

		double startClosePrice = startBar.getClosePrice().doubleValue();
		double currentClosePrice = currentBar.getClosePrice().doubleValue();

		double change = ((currentClosePrice - startClosePrice) / startClosePrice) * 100;
		return (Double.parseDouble(df.format(change)));
	}

	public static void main(String args[]) {
		System.out.println(getReturnsLastNIntervals(DataUtil.getTimeSeries(StockSymbols.IBULHSGFIN.name, CandleStickInterval.DAY), 20));
	}
}