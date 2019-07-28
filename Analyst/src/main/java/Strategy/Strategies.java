package Strategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ta4j.core.Bar;
import org.ta4j.core.Rule;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.trading.rules.CrossedDownIndicatorRule;
import org.ta4j.core.trading.rules.CrossedUpIndicatorRule;

import Constants.CandleStickInterval;
import Constants.StockSymbols;
import File.FileReader;
import File.FileReaderUtil;
import File.XLSCreator;
import Statistics.StatisticsCollector;

public class Strategies {

	public static TimeSeries getTimeSeries(String stock, String interval) {
		String fileLocation = FileReaderUtil.getFileLocation(stock, interval);
		TimeSeries timeSeries = FileReaderUtil.convertToTimeseries(FileReader.getCandeStickData(fileLocation));
		return timeSeries;
	}

	public static StatisticsCollector EMA(String stock, String interval, int shortInterval, int longInterval) {
		Map<String, String> statsMeta = new LinkedHashMap<String, String>();
		statsMeta.put("Stock", stock);
		statsMeta.put("Interval", interval);
		statsMeta.put("EMA-Short", shortInterval + "");
		statsMeta.put("EMA-Long", longInterval + "");

		TimeSeries timeSeries = getTimeSeries(stock, interval);
		ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(timeSeries);

		EMAIndicator shortSma = new EMAIndicator(closePriceIndicator, shortInterval);
		EMAIndicator longSma = new EMAIndicator(closePriceIndicator, longInterval);

		Rule buyingRule = new CrossedUpIndicatorRule(shortSma, longSma);
		Rule sellingRule = new CrossedDownIndicatorRule(shortSma, longSma);

		StatisticsCollector statsCollector = new StatisticsCollector();
		statsCollector.statsMeta = statsMeta;
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

		return statsCollector;
	}

	public static StatisticsCollector findEMASetting(String stock, String interval) {
		int maxProfiti = 0;
		int maxProfitj = 0;
		StatisticsCollector statsCollectorMax = null;
		for (int i = 8; i <= 8; i++) {
			for (int j = 20; j <= 20; j++) {
				StatisticsCollector statsCollector = EMA(stock, interval, i, j);
				float netProfit = statsCollector.statistics.grossProfit - statsCollector.statistics.charges;
				float maxProfit = 0;
				if (statsCollectorMax != null) {
					maxProfit = statsCollectorMax.statistics.grossProfit - statsCollectorMax.statistics.charges;
				}
				if (netProfit > maxProfit) {
					maxProfiti = i;
					maxProfitj = j;
					statsCollectorMax = statsCollector;
				}
			}
		}
		System.out.println();
		System.out.println(
				"Stock :" + stock + " Interval : " + interval + " " + " EMA : " + maxProfiti + "-" + maxProfitj);
		if (statsCollectorMax != null) {
			statsCollectorMax.printStats(false);
		}
		return statsCollectorMax;
	}

	public static List<StatisticsCollector> getEMASettingForStockAllIntervals(String stock) {
		List<StatisticsCollector> statsCollectorList = new ArrayList<StatisticsCollector>();
		statsCollectorList.add(findEMASetting(stock, CandleStickInterval.MINUTE_3));
		statsCollectorList.add(findEMASetting(stock, CandleStickInterval.MINUTE_5));
		statsCollectorList.add(findEMASetting(stock, CandleStickInterval.MINUTE_10));
		statsCollectorList.add(findEMASetting(stock, CandleStickInterval.MINUTE_15));
		statsCollectorList.add(findEMASetting(stock, CandleStickInterval.MINUTE_30));
		statsCollectorList.add(findEMASetting(stock, CandleStickInterval.MINUTE_60));
		statsCollectorList.add(findEMASetting(stock, CandleStickInterval.DAY));
		while (statsCollectorList.contains(null)) {
			statsCollectorList.remove(null);
		}
		return statsCollectorList;
	}

	public static void main(String args[]) throws IOException {
		List<StatisticsCollector> statsCollectorList = getEMASettingForStockAllIntervals(StockSymbols.BANK_NIFTY);
		XLSCreator.generateXLS(statsCollectorList, "test");
		/*getEMASettingForStockAllIntervals(StockSymbols.NIFTY);
		getEMASettingForStockAllIntervals(StockSymbols.HDFC_BANK);
		getEMASettingForStockAllIntervals(StockSymbols.BAJAJ_FINANCE);
		getEMASettingForStockAllIntervals(StockSymbols.KOTAK_BANK);
		getEMASettingForStockAllIntervals(StockSymbols.RELIANCE);
		getEMASettingForStockAllIntervals(StockSymbols.INDUSIND_BANK);
		getEMASettingForStockAllIntervals(StockSymbols.MARUTI_SUZUKI);
		getEMASettingForStockAllIntervals(StockSymbols.YES_BANK);
		getEMASettingForStockAllIntervals(StockSymbols.RBL_BANK);
		getEMASettingForStockAllIntervals(StockSymbols.AXIS_BANK);
		getEMASettingForStockAllIntervals(StockSymbols.ICICI_BANK);
		getEMASettingForStockAllIntervals(StockSymbols.SBI_BANK);
		getEMASettingForStockAllIntervals(StockSymbols.HDFC);*/
	}
}
