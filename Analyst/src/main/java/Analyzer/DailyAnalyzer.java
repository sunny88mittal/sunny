package Analyzer;

import java.io.IOException;

import Data.StocksDataDownloader;

public class DailyAnalyzer {

	public static void main(String args[]) throws IOException {
		StocksDataDownloader.updateDailyDataAllStocks();
	}
}