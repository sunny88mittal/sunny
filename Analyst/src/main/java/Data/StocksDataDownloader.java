package Data;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Constants.CandleStickInterval;
import Constants.FileConstants;
import Constants.StockSymbols;

public class StocksDataDownloader {

	public static String getRealTimeData(StockSymbols stock, String interval) throws IOException {
		String url = URLConstants.REAL_TIME_URL.replaceAll("SYMBOL", stock.code).replaceAll("INTERVAL", interval)
				.replaceAll("TODATE", getTodaysDate());
		String data = NetworkHelper.makeGetRequest(url);
		return data;
	}

	private static void getData(String stockName, String stockSymbol, String interval) throws IOException {
		IOHelper.createDirIfReq(FileConstants.STOCKS_DATA_FILE_BASE_PATH, stockName);

		String url = URLConstants.DATA_URL.replace("SYMBOL", stockSymbol).replace("INTERVAL", interval).replace("TODATE",
				getTodaysDate());
		String fileLocation = FileConstants.STOCKS_DATA_FILE_BASE_PATH + "\\" + stockName + "\\" + interval + ".json";

		if (IOHelper.isDataAlreadyUpdated(fileLocation)) {
			System.out.println("Data already updated for " + stockName + " with interval " + interval);
			return;
		}

		String data = NetworkHelper.makeGetRequest(url);
		IOHelper.writeToFile(fileLocation, data);
		System.out.println("Data updated for " + stockName + " with interval " + interval);
	}

	private static String getTodaysDate() {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = new Date();
		return simpleDateFormat.format(date);
	}

	private static void getDataForStock(StockSymbols stockSymbol) throws IOException {
		for (String interval : CandleStickInterval.getAllIntervals()) {
			getData(stockSymbol.name, stockSymbol.code, interval);
		}
	}

	public static void updateDailyDataAllStocks() throws IOException {
		List<StockSymbols> stocks = StockSymbols.getAllStocksList();
		for (StockSymbols stock : stocks) {
			StocksDataDownloader.getData(stock.name, stock.code, CandleStickInterval.DAY);
			System.out.println("Data Updated for :" + stock.name);
		}
	}

	public static void updateAllDataForStocks(List<StockSymbols> stocks) throws IOException {
		for (StockSymbols stock : stocks) {
			System.out.println("Updating data for : " + stock.name);
			getDataForStock(stock);
			System.out.println("Data Updated for :" + stock.name);
		}
	}

	public static void main(String args[]) throws IOException, InterruptedException {
		// getDataForStock(StockSymbols.AXISBANK);
		boolean updated = false;
		while (!updated) {
			try {
				updateAllDataForStocks(StockSymbols.getAllStocksList());
				updated = true;
			} catch (Exception e) {
				Thread.sleep(5000);
			}
		}
	}
}
