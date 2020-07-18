package Indicators;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Constants.FileConstants;
import Constants.StockSymbols;
import File.FileReader;

public class GapAnalyzer {

	public static List<String> checkGapStocks(LocalDate date, double threshold) throws FileNotFoundException {
		List<String> gapStocks = new ArrayList<String>();

		List<String> stocksData = getStocksData(date);
		List<String> gapUps = checkForGapUps(stocksData, threshold);
		List<String> gapDowns = checkForGapDowns(stocksData, threshold);

		List<StockSymbols> whiteListedStocks = StockSymbols.getAllStocksList();
		Set<String> whiteListedStocksSet = new HashSet<String>();
		for (StockSymbols stockSymbol : whiteListedStocks) {
			whiteListedStocksSet.add(stockSymbol.name);
		}

		for (String gapUp : gapUps) {
			if (whiteListedStocksSet.contains(gapUp)) {
				gapStocks.add(gapUp);
			}
		}

		for (String gapDown : gapDowns) {
			if (whiteListedStocksSet.contains(gapDown)) {
				gapStocks.add(gapDown);
			}
		}

		return gapStocks;
	}

	private static List<String> checkForGapDowns(List<String> stockData, double threshold) {
		List<String> gapUps = new ArrayList<String>();

		for (int i = 1; i < stockData.size(); i++) {
			String stockDataEntry = stockData.get(i);
			String tokens[] = stockDataEntry.split(",");
			String symbol = tokens[0];
			String series = tokens[1];
			double open = Double.parseDouble(tokens[2]);
			double previousClose = Double.parseDouble(tokens[7]);
			if (series.equals("EQ") && (previousClose - open) * 100 / open > threshold) {
				gapUps.add(symbol);
			}
		}

		return gapUps;
	}

	private static List<String> checkForGapUps(List<String> stockData, double threshold) {
		List<String> gapUps = new ArrayList<String>();

		for (int i = 1; i < stockData.size(); i++) {
			String stockDataEntry = stockData.get(i);
			String tokens[] = stockDataEntry.split(",");
			String symbol = tokens[0];
			String series = tokens[1];
			double open = Double.parseDouble(tokens[2]);
			double previousClose = Double.parseDouble(tokens[7]);
			if (series.equals("EQ") && (open - previousClose) * 100 / previousClose > threshold) {
				gapUps.add(symbol);
			}
		}

		return gapUps;
	}

	private static List<String> getStocksData(LocalDate date) throws FileNotFoundException {
		String dateValue = date.getDayOfMonth() + "";
		dateValue = dateValue.length() == 1 ? "0" + dateValue : dateValue;
		String yearValue = date.getYear() + "";
		String monValue = date.getMonth().name().substring(0, 3);

		String fileLocation = FileConstants.STOCKS_DATA_FILE_NAME.replace("DD", dateValue).replace("MON", monValue)
				.replace("YYYY", yearValue);

		List<String> stocksData = FileReader.getCSVData(fileLocation);
		return stocksData;
	}

	public static void main(String args[]) throws FileNotFoundException {
		List<String> stockWithGaps = checkGapStocks(LocalDate.now().minusDays(1), 2);
		System.out.println(stockWithGaps);
	}
}
