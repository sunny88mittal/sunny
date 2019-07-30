package Data;

import java.io.IOException;

import Constants.CandleStickInterval;
import Constants.FileConstants;
import Constants.StockSymbols;

public class StocksDataDownloader {

	private static final String URL = "https://kitecharts-aws.zerodha.com" + "/api/chart/SYMBOL/INTERVAL?"
			+ "public_token=px7RlNQX3W7rg9Vm2WOUDbGzo6WBxUqy&" + "user_id=YF3220&oi=1" + "&api_key=kitefront&"
			+ "access_token=&" + "from=2014-01-01" + "&to=2019-07-28&" + "ciqrandom=1564301727399";

	public static void getData(String stockName, String stockSymbol, String interval) throws IOException {
		IOHelper.createDirIfReq(FileConstants.DATA_FILE_BASE_PATH, stockName);
		String url = URL.replace("SYMBOL", stockSymbol).replace("INTERVAL", interval);
		String fileLocation = FileConstants.DATA_FILE_BASE_PATH + "\\" + stockName + "\\" + interval + ".json";
		String data = NetworkHelper.makeGetRequest(url);
		IOHelper.writeToFile(fileLocation, data);
	}
	
	private static void getDataForStock(String stockName, String stockSymbol) throws IOException {
		getData(stockName, stockSymbol, CandleStickInterval.MINUTE_3);
		getData(stockName, stockSymbol, CandleStickInterval.MINUTE_5);
		getData(stockName, stockSymbol, CandleStickInterval.MINUTE_10);
		getData(stockName, stockSymbol, CandleStickInterval.MINUTE_15);
		getData(stockName, stockSymbol, CandleStickInterval.MINUTE_30);
		getData(stockName, stockSymbol, CandleStickInterval.MINUTE_60);
		getData(stockName, stockSymbol, CandleStickInterval.DAY);
	}

	public static void main(String args[]) throws IOException {
		getDataForStock(StockSymbols.SBI_BANK, "779521");
	}
}
