package DataDownloader;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Constants.CandleStickInterval;
import Constants.FileConstants;
import Constants.StockSymbols;

public class DataDownloader {

	private static final String URL = "https://kitecharts-aws.zerodha.com" + "/api/chart/SYMBOL/INTERVAL?"
			+ "public_token=px7RlNQX3W7rg9Vm2WOUDbGzo6WBxUqy&" + "user_id=YF3220&oi=1" + "&api_key=kitefront&"
			+ "access_token=&" + "from=2014-01-01" + "&to=2019-07-28&" + "ciqrandom=1564301727399";

	public static void getData(String stockName, String stockSymbol, String interval) throws IOException {
		String url = URL.replace("SYMBOL", stockSymbol).replace("INTERVAL", interval);
		String fileLocation = FileConstants.FILE_BASE_PATH + "\\" + stockName + "\\" + interval + ".json";
		String data = makeGetRequest(url);
		writeToFile(fileLocation, data);
	}

	public static String makeGetRequest(String url) throws IOException {
		String USER_AGENT = "Mozilla/5.0";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}

	public static void writeToFile(String fileLocation, String data) throws IOException {
		FileWriter fileWriter = new FileWriter(fileLocation);
		fileWriter.write(data);
		fileWriter.flush();
		fileWriter.close();
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
		getDataForStock(StockSymbols.AXIS_BANK, "1510401");
		getDataForStock(StockSymbols.ICICI_BANK, "1270529");
		getDataForStock(StockSymbols.YES_BANK, "3050241");
		getDataForStock(StockSymbols.RBL_BANK, "4708097");
		getDataForStock(StockSymbols.INDUSIND_BANK, "1346049");
	}
}
