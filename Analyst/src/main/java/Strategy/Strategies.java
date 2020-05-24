package Strategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ta4j.core.Bar;
import org.ta4j.core.Rule;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.ATRIndicator;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.ParabolicSarIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.volume.VWAPIndicator;
import org.ta4j.core.trading.rules.CrossedDownIndicatorRule;
import org.ta4j.core.trading.rules.CrossedUpIndicatorRule;

import Constants.CandleStickInterval;
import Constants.FileConstants;
import Constants.StockSymbols;
import DataUtil.DataUtil;
import File.XLSCreator;
import Indicators.MACDWithSignalIndicator;
import Indicators.SuperTrendIndicator;
import Trading.TradeUtil;
import Trading.TradesCollector;

public class Strategies {

	public static TradesCollector EMA(String stock, String interval, int shortInterval, int longInterval) {
		Map<String, String> statsMeta = new LinkedHashMap<String, String>();
		statsMeta.put("Stock", stock);
		statsMeta.put("Interval", interval);
		statsMeta.put("EMA-Short", shortInterval + "");
		statsMeta.put("EMA-Long", longInterval + "");

		TimeSeries timeSeries = DataUtil.getTimeSeries(stock, interval);
		ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(timeSeries);

		EMAIndicator shortSma = new EMAIndicator(closePriceIndicator, shortInterval);
		EMAIndicator longSma = new EMAIndicator(closePriceIndicator, longInterval);

		Rule buyingRule = new CrossedUpIndicatorRule(shortSma, longSma);
		Rule sellingRule = new CrossedDownIndicatorRule(shortSma, longSma);

		TradesCollector statsCollector = new TradesCollector();
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

	public static TradesCollector EMAMACD(String stock, String interval, int maShortInterval, int maLongInterval,
			int macdSignalInterval, int macdShortInterval, int macdLongInterval) {
		TimeSeries timeSeries = DataUtil.getTimeSeries(stock, interval);
		ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(timeSeries);

		EMAIndicator shortSma = new EMAIndicator(closePriceIndicator, maShortInterval);
		EMAIndicator longSma = new EMAIndicator(closePriceIndicator, maLongInterval);

		MACDIndicator macdIndicator = new MACDIndicator(closePriceIndicator, macdShortInterval, macdLongInterval);

		Rule buyingRule = new CrossedUpIndicatorRule(shortSma, longSma);
		Rule sellingRule = new CrossedDownIndicatorRule(shortSma, longSma);

		int barsCount = timeSeries.getBarCount();
		TradesCollector statsCollector = new TradesCollector();

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

	public static TradesCollector findEMASetting(String stock, String interval) {
		int maxProfiti = 0;
		int maxProfitj = 0;
		TradesCollector statsCollectorMax = null;
		for (int i = 4; i <= 8; i++) {
			for (int j = (int) (i * 1.5); j <= i * 2.5; j++) {
				TradesCollector statsCollector = EMA(stock, interval, i, j);
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
		return statsCollectorMax;
	}

	public static List<TradesCollector> getEMASettingForStockAllIntervals(String stock) {
		List<TradesCollector> statsCollectorList = new ArrayList<TradesCollector>();
		/*
		 * statsCollectorList.add(findEMASetting(stock, CandleStickInterval.MINUTE_3));
		 * statsCollectorList.add(findEMASetting(stock, CandleStickInterval.MINUTE_5));
		 * statsCollectorList.add(findEMASetting(stock, CandleStickInterval.MINUTE_10));
		 * statsCollectorList.add(findEMASetting(stock, CandleStickInterval.MINUTE_15));
		 * statsCollectorList.add(findEMASetting(stock, CandleStickInterval.MINUTE_30));
		 * statsCollectorList.add(findEMASetting(stock, CandleStickInterval.MINUTE_60));
		 */
		statsCollectorList.add(findEMASetting(stock, CandleStickInterval.DAY));
		while (statsCollectorList.contains(null)) {
			statsCollectorList.remove(null);
		}
		return statsCollectorList;
	}

	public static void printPSARValues(String stock, String interval) {
		TimeSeries timeSeries = DataUtil.getTimeSeries(stock, interval);
		ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(timeSeries);

		VWAPIndicator vwapIndicator = new VWAPIndicator(timeSeries, 1);
		MACDWithSignalIndicator macd = new MACDWithSignalIndicator(closePriceIndicator);
		ParabolicSarIndicator psar = new ParabolicSarIndicator(timeSeries);
		TradesCollector statsCollector = new TradesCollector();

		for (int j = 1; j <= 1; j++) {
			Bar startBar = null;
			int i = timeSeries.getBarCount() - (125 * j);
			int k = 1;
			for (; i < timeSeries.getBarCount() - (125 * (j - 1)); i++) {
				Bar bar = timeSeries.getBar(i);
				vwapIndicator = new VWAPIndicator(timeSeries, k++);
				// int vwapValue = vwapIndicator.getValue(i).intValue();
				int psarValue = psar.getValue(i).intValue();
				float macdValue = macd.getValue(i).floatValue();
				int closePrice = bar.getClosePrice().intValue();
				if (/* (closePrice > vwapValue) && */ (psarValue < closePrice) && (macdValue > (float) 0.0)) {
					System.out.println(bar.getSimpleDateName() + ": Buy " + " Close Price : " + bar.getClosePrice());
					if (startBar == null) {
						startBar = bar;
					}
				} else if ((psarValue > closePrice) && (macdValue < (float) 0.0)) {
					System.out.println(bar.getSimpleDateName() + " : Sell " + " Close Price : " + bar.getClosePrice());
					if (startBar != null) {
						statsCollector.addDataPoint(startBar, timeSeries.getBar(i));
					}
					startBar = null;
				} else {
					System.out.println(bar.getSimpleDateName() + " : Wait " + " Close Price : " + bar.getClosePrice());
				}
				/*
				 * System.out.println(bar.getSimpleDateName() + "Close Price : " +
				 * bar.getClosePrice() + " PSAR : " + psarValue + " VWAP  :" + vwapValue +
				 * " MACD : " + macdValue);
				 */
			}

			// Trend did not end till EOD
			if (startBar != null) {
				statsCollector.addDataPoint(startBar, timeSeries.getBar(i - 1));
				startBar = null;
			}
		}

		// statsCollector.printStats(true);
	}

	private static void SuperTrendStartegy(String stock, String interval) {
		TimeSeries timeSeries = DataUtil.getTimeSeries(stock, interval);
		SuperTrendIndicator st = new SuperTrendIndicator(timeSeries, 7, 3);
		boolean isSell = false;
		boolean isBuy = false;
		for (int j = 220; j < timeSeries.getBarCount(); j++) {
			Bar bar = timeSeries.getBar(j);
			int closePrice = bar.getClosePrice().intValue();
			if (st.getValue(j).intValue() < closePrice) {
				if (!isBuy) {
					isBuy = true;
					isSell = false;
					System.out.println(bar.getSimpleDateName() + ": Buy " + " Close Price : " + bar.getClosePrice());
				}
			} else {
				if (!isSell) {
					isBuy = false;
					isSell = true;
					System.out.println(bar.getSimpleDateName() + ": Sell " + " Close Price : " + bar.getClosePrice());
				}
			}
		}
	}

	public static void main(String args[]) throws IOException {
		// printPSARValues(StockSymbols.AXISBANK.name, CandleStickInterval.MINUTE_3);
		// List<StatisticsCollector> statsCollectorList =
		// getEMASettingForStockAllIntervals(StockSymbols.BANK_NIFTY);
		// statsCollectorList.addAll(getEMASettingForStockAllIntervals(StockSymbols.NIFTY));
		/*
		 * statsCollectorList.addAll(getEMASettingForStockAllIntervals(StockSymbols.
		 * HDFC_BANK));
		 * statsCollectorList.addAll(getEMASettingForStockAllIntervals(StockSymbols.
		 * BAJAJ_FINANCE));
		 * statsCollectorList.addAll(getEMASettingForStockAllIntervals(StockSymbols.
		 * KOTAK_BANK));
		 * statsCollectorList.addAll(getEMASettingForStockAllIntervals(StockSymbols.
		 * RELIANCE));
		 * statsCollectorList.addAll(getEMASettingForStockAllIntervals(StockSymbols.
		 * INDUSIND_BANK));
		 * statsCollectorList.addAll(getEMASettingForStockAllIntervals(StockSymbols.
		 * MARUTI_SUZUKI));
		 * statsCollectorList.addAll(getEMASettingForStockAllIntervals(StockSymbols.
		 * YES_BANK));
		 * statsCollectorList.addAll(getEMASettingForStockAllIntervals(StockSymbols.
		 * RBL_BANK));
		 * statsCollectorList.addAll(getEMASettingForStockAllIntervals(StockSymbols.
		 * AXIS_BANK));
		 * statsCollectorList.addAll(getEMASettingForStockAllIntervals(StockSymbols.
		 * ICICI_BANK));
		 * statsCollectorList.addAll(getEMASettingForStockAllIntervals(StockSymbols.
		 * SBI_BANK));
		 * statsCollectorList.addAll(getEMASettingForStockAllIntervals(StockSymbols.HDFC
		 * ));
		 */
		// XLSCreator.generateXLS(statsCollectorList, "EMA Analysis");

		/*
		 * List<TradesCollector> statsCollectorList = new ArrayList<TradesCollector>();
		 * for (StockSymbols stockSymbol : StockSymbols.getNiftyStocksList()) {
		 * statsCollectorList.addAll(getEMASettingForStockAllIntervals(stockSymbol.name)
		 * ); } String fileLocation = FileConstants.ANALYSIS_FILE_BASE_PATH +
		 * "EMA Analysis Nifty";
		 * XLSCreator.generateXLS(TradeUtil.getExcelSheetContent(statsCollectorList),
		 * fileLocation);
		 */
		SuperTrendStartegy(StockSymbols.BANKNIFTY.name, CandleStickInterval.MINUTE_15);
	}
}
