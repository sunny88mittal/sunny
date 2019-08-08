package Analyzer;

import java.io.IOException;

import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.ParabolicSarIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import Constants.CandleStickInterval;
import Constants.StockSymbols;
import Data.StocksDataDownloader;
import DataUtil.DataUtil;
import Indicators.MACDWithSignalIndicator;

public class DailyAnalyzer {

	public static void main(String args[]) throws IOException {
		StocksDataDownloader.updateDailyDataAllStocks();
		for (StockSymbols stockSymbol : StockSymbols.getNiftyHeavyStocksList()) {
			checkDaily(stockSymbol, CandleStickInterval.DAY);
		}
	}

	private static void checkDaily(StockSymbols stockSymbol, String candleStickInterval) throws IOException {
		TimeSeries series = DataUtil.getTimeSeries(stockSymbol.name, candleStickInterval);
		ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);

		MACDWithSignalIndicator macd = new MACDWithSignalIndicator(closePriceIndicator);
		ParabolicSarIndicator psar = new ParabolicSarIndicator(series);

		int index = series.getEndIndex();
		Bar bar = series.getBar(index);

		int psarValue = psar.getValue(index).intValue();
		float macdValue = macd.getValue(index).floatValue();
		int closePrice = bar.getClosePrice().intValue();

		if ((psarValue < closePrice) && (macdValue > (float) 0.0)) {
			System.out.println(stockSymbol.name + " " + bar.getSimpleDateName() + ": Buy " + " Close Price : "
					+ bar.getClosePrice());
		} else if ((psarValue > closePrice) && (macdValue < (float) 0.0)) {
			System.out.println(stockSymbol.name + " " + bar.getSimpleDateName() + " : Sell " + " Close Price : "
					+ bar.getClosePrice());
		} else {
			System.out.println(stockSymbol.name + " " + bar.getSimpleDateName() + " : Wait " + " Close Price : "
					+ bar.getClosePrice());
		}
	}
}