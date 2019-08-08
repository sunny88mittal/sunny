package Analyzer;

import java.io.IOException;
import java.time.LocalDateTime;

import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.ParabolicSarIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import Constants.CandleStickInterval;
import Constants.StockSymbols;
import Data.StocksDataDownloader;
import DataUtil.DataUtil;
import Indicators.MACDWithSignalIndicator;

public class RealTimeTracker {

	public static void trackRealTime(StockSymbols stockSymbol, String candleStickInterval)
			throws IOException, InterruptedException {
		int sleepFor = 1000;
		int lastCheckedAt = -1;
		while (true) {
			int currentMinute = getCurrentMinute();
			if (currentMinute % 3 == 0 && currentMinute != lastCheckedAt) {
				check3Min(stockSymbol, candleStickInterval);
				lastCheckedAt = currentMinute;
			}
			Thread.sleep(sleepFor);
		}
	}

	private static void check3Min(StockSymbols stockSymbol, String candleStickInterval) throws IOException {
		String data = StocksDataDownloader.getRealTimeData(stockSymbol, candleStickInterval);
		TimeSeries series = DataUtil.convertToTimeseries(DataUtil.getCandeStickData(data));
		ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);

		MACDWithSignalIndicator macd = new MACDWithSignalIndicator(closePriceIndicator);
		ParabolicSarIndicator psar = new ParabolicSarIndicator(series);

		int index = series.getEndIndex();
		Bar bar = series.getBar(index);

		int psarValue = psar.getValue(index).intValue();
		float macdValue = macd.getValue(index).floatValue();
		int closePrice = bar.getClosePrice().intValue();

		if ((psarValue < closePrice) && (macdValue > (float) 0.0)) {
			System.out.println(bar.getSimpleDateName() + ": Buy " + " Close Price : " + bar.getClosePrice());
		} else if ((psarValue > closePrice) && (macdValue < (float) 0.0)) {
			System.out.println(bar.getSimpleDateName() + " : Sell " + " Close Price : " + bar.getClosePrice());
		} else {
			System.out.println(bar.getSimpleDateName() + " : Wait " + " Close Price : " + bar.getClosePrice());
		}
	}

	private static int getCurrentMinute() {
		return LocalDateTime.now().getMinute();
	}

	public static void main(String args[]) throws IOException, InterruptedException {
		trackRealTime(StockSymbols.BAJFINANCE, CandleStickInterval.MINUTE_3);
	}
}