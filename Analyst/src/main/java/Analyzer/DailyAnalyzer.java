package Analyzer;

import java.io.IOException;
import java.util.List;

import Constants.CandleStickInterval;
import Constants.StockSymbols;
import Data.StocksDataDownloader;

public class DailyAnalyzer {

	private static void updateData() throws IOException {
		List<StockSymbols> stocks = StockSymbols.getAllStocksList();
		for (StockSymbols stock : stocks) {
			StocksDataDownloader.getData(stock.name, stock.code, CandleStickInterval.DAY);
			System.out.println("Data Updated for :" + stock.name);
		}
	}

	public static void main(String args[]) throws IOException {
		updateData();
	}
}