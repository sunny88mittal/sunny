package Data;

import java.io.IOException;

import Constants.CandleStickInterval;
import Constants.FileConstants;
import Constants.StockSymbols;

public class StocksDataDownloader {

	private static final String URL = "https://kitecharts-aws.zerodha.com" + "/api/chart/SYMBOL/INTERVAL?"
			+ "public_token=px7RlNQX3W7rg9Vm2WOUDbGzo6WBxUqy&" + "user_id=YF3210&oi=1" + "&api_key=kitefront&"
			+ "access_token=&" + "from=2014-01-01" + "&to=2019-08-02&" + "ciqrandom=1564301727399";

	public static void getData(String stockName, String stockSymbol, String interval) throws IOException {
		IOHelper.createDirIfReq(FileConstants.DATA_FILE_BASE_PATH, stockName);
		String url = URL.replace("SYMBOL", stockSymbol).replace("INTERVAL", interval);
		String fileLocation = FileConstants.DATA_FILE_BASE_PATH + "\\" + stockName + "\\" + interval + ".json";
		String data = NetworkHelper.makeGetRequest(url);
		IOHelper.writeToFile(fileLocation, data);
	}

	private static void getDataForStock(String stockName, String stockSymbol) throws IOException {
		for (String interval : CandleStickInterval.getAllIntervals()) {
			getData(stockName, stockSymbol, interval);
		}
	}

	public static void main(String args[]) throws IOException {
		getDataForStock(StockSymbols.BANK_NIFTY.name, "260105");
	}
}
