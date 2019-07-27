package Strategy;

import org.ta4j.core.Bar;
import org.ta4j.core.Rule;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.trading.rules.CrossedDownIndicatorRule;
import org.ta4j.core.trading.rules.CrossedUpIndicatorRule;

import Constants.CandlsestickInterval;
import Constants.StockSymbols;
import FileReader.FileReader;
import FileReader.FileReaderUtil;
import Statistics.StatisticsCollector;

public class Strategies {

	public static TimeSeries getTimeSeries(String stock, String interval) {
		String fileLocation = FileReaderUtil.getFileLocation(stock, interval);
		TimeSeries timeSeries = FileReaderUtil.convertToTimeseries(FileReader.getCandeStickData(fileLocation));
		return timeSeries;
	}

	public static StatisticsCollector EMA(String stock, String interval, int shortInterval, int longInterval) {
		TimeSeries timeSeries = getTimeSeries(stock, interval);
		ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(timeSeries);

		EMAIndicator shortSma = new EMAIndicator(closePriceIndicator, shortInterval);
		EMAIndicator longSma = new EMAIndicator(closePriceIndicator, longInterval);

		Rule buyingRule = new CrossedUpIndicatorRule(shortSma, longSma);
		Rule sellingRule = new CrossedDownIndicatorRule(shortSma, longSma);

		StatisticsCollector statsCollector = new StatisticsCollector();
		int barsCount = timeSeries.getBarCount();

		Bar startBar = null;
		for (int i = 11; i < barsCount; i++) {
			if (buyingRule.isSatisfied(i)) {
				// System.out.println("Buy at :" + timeSeries.getBar(i));
				startBar = timeSeries.getBar(i);
			}
			if (sellingRule.isSatisfied(i)) {
				// System.out.println("Sell at :" + timeSeries.getBar(i));
				if (startBar != null) {
					statsCollector.addDataPoint(startBar, timeSeries.getBar(i));
				}
			}
		}

		System.out.println();
		System.out.println("----Results for " + interval + " ----");
		statsCollector.statistics.printStats();
		return statsCollector;
	}

	public static StatisticsCollector EMAMACD(String stock, String interval, int maShortInterval, int maLongInterval,
			int macdSignalInterval, int macdShortInterval, int macdLongInterval) {
		TimeSeries timeSeries = getTimeSeries(stock, interval);
		ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(timeSeries);

		EMAIndicator shortSma = new EMAIndicator(closePriceIndicator, maShortInterval);
		EMAIndicator longSma = new EMAIndicator(closePriceIndicator, maLongInterval);

		MACDIndicator macdIndicator = new MACDIndicator(closePriceIndicator, macdShortInterval, macdLongInterval);

		Rule buyingRule = new CrossedUpIndicatorRule(shortSma, longSma);
		Rule sellingRule = new CrossedDownIndicatorRule(shortSma, longSma);

		int barsCount = timeSeries.getBarCount();
		StatisticsCollector statsCollector = new StatisticsCollector();

		Bar startBar = null;

		for (int i = 11; i < barsCount; i++) {
			if (buyingRule.isSatisfied(i) && macdIndicator.getValue(i).floatValue() > 0.0) {
				// System.out.println("Buy at :" + timeSeries.getBar(i));
				startBar = timeSeries.getBar(i);
			}
			if (sellingRule.isSatisfied(i) || macdIndicator.getValue(i).floatValue() < 0.0) {
				// System.out.println("Sell at :" + timeSeries.getBar(i));
				if (startBar != null) {
					statsCollector.addDataPoint(startBar, timeSeries.getBar(i));
				}
			}
		}

		System.out.println();
		System.out.println("----Results for " + interval + " ----");
		statsCollector.statistics.printStats();
		return statsCollector;
	}

	public static void main(String args[]) {
		String stock = StockSymbols.BAJAJ_FINANCE;
		int sma = 4;
		int lma = 10;

		int macdSignal = 7;
		int macdShort = 10;
		int macdLong = 21;

		EMA(stock, CandlsestickInterval.MINUTE_3, sma, lma);
		EMA(stock, CandlsestickInterval.MINUTE_5, sma, lma);
		EMA(stock, CandlsestickInterval.MINUTE_10, sma, lma);
		EMA(stock, CandlsestickInterval.MINUTE_15, sma, lma);
		EMA(stock, CandlsestickInterval.MINUTE_30, sma, lma);
		EMA(stock, CandlsestickInterval.MINUTE_60, sma, lma);
		EMA(stock, CandlsestickInterval.DAY, sma, lma);

		/*
		 * EMAMACD(StockSymbols.BAJAJ_FINANCE, CandlsestickInterval.MINUTE_3, sma, lma,
		 * macdSignal, macdShort, macdLong); EMAMACD(StockSymbols.BAJAJ_FINANCE,
		 * CandlsestickInterval.MINUTE_5, sma, lma, macdSignal, macdShort, macdLong);
		 * EMAMACD(StockSymbols.BAJAJ_FINANCE, CandlsestickInterval.MINUTE_10, sma, lma,
		 * macdSignal, macdShort, macdLong); EMAMACD(StockSymbols.BAJAJ_FINANCE,
		 * CandlsestickInterval.MINUTE_30, sma, lma, macdSignal, macdShort, macdLong);
		 * EMAMACD(StockSymbols.BAJAJ_FINANCE, CandlsestickInterval.MINUTE_60, sma, lma,
		 * macdSignal, macdShort, macdLong); EMAMACD(StockSymbols.BAJAJ_FINANCE,
		 * CandlsestickInterval.DAY, sma, lma, macdSignal, macdShort, macdLong);
		 */
	}
}
