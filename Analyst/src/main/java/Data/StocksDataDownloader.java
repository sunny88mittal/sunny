package Data;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import Constants.CandleStickInterval;
import Constants.FileConstants;
import Constants.StockSymbols;

public class StocksDataDownloader {

	private static final String URL = "https://kitecharts-aws.zerodha.com" + "/api/chart/SYMBOL/INTERVAL?"
			+ "public_token=px7RlNQX3W7rg9Vm2WOUDbGzo6WBxUqy&" + "user_id=YF3210&oi=1" + "&api_key=kitefront&"
			+ "access_token=&" + "from=2014-01-01" + "&to=TODATE&" + "ciqrandom=1564301727399";
	
	private static final String REAL_TIME_URL = "https://kitecharts-aws.zerodha.com" + "/api/chart/SYMBOL/INTERVAL?"
			+ "public_token=px7RlNQX3W7rg9Vm2WOUDbGzo6WBxUqy&" + "user_id=YF3210&oi=1" + "&api_key=kitefront&"
			+ "access_token=&" + "from=TODATE" + "&to=TODATE&" + "ciqrandom=1564301727399";
	
	public static String getRealTimeData(StockSymbols stock, String interval) throws IOException {
		String url = REAL_TIME_URL.replaceAll("SYMBOL", stock.code).
				replaceAll("INTERVAL", interval).
				replaceAll("TODATE", getTodaysDate());
		String data = NetworkHelper.makeGetRequest(url);
		return data;
	}

	private static void getData(String stockName, String stockSymbol, String interval) throws IOException {
		IOHelper.createDirIfReq(FileConstants.DATA_FILE_BASE_PATH, stockName);
		
		String url = URL.replace("SYMBOL", stockSymbol).
				replace("INTERVAL", interval).
				replace("TODATE", getTodaysDate());
		String fileLocation = FileConstants.DATA_FILE_BASE_PATH + "\\" + stockName + "\\" + interval + ".json";
		
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
		Date date =  new Date();
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
	
	public static void updateAllDataAllStocks() throws IOException {
		List<StockSymbols> stocks = StockSymbols.getAllStocksList();
		for (StockSymbols stock : stocks) {
			System.out.println("Updating data for : " + stock.name);
			getDataForStock(stock);
			System.out.println("Data Updated for :" + stock.name);
		}
	}

	public static void main(String args[]) throws IOException {
		getDataForStock(StockSymbols.AXISBANK);
		//updateAllDataAllStocks();
	}
}
