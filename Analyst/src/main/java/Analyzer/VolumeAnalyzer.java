package Analyzer;

import org.ta4j.core.TimeSeries;

import Constants.CandleStickInterval;
import Constants.StockSymbols;
import DataUtil.DataUtil;

public class VolumeAnalyzer {

	private static void printVolumeStats(StockSymbols stockSymbol, String candleStickInterval) {
		TimeSeries series = DataUtil.getTimeSeries(stockSymbol.name, candleStickInterval);
		int from = 1250;
		long volume = 0;
		for (int i = series.getBarCount() - from; i < series.getBarCount(); i++) {
			volume += series.getBar(i).getVolume().longValue();
		}
		System.out.println("Mean is : " + volume/from);
	}

	public static void main(String args[]) {
		printVolumeStats(StockSymbols.BAJFINANCE, CandleStickInterval.MINUTE_3);
	}
}
