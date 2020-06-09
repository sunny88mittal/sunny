package Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class NetworkHelper {

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
			response.append("\n");
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}
	
	public static byte[] makeGetRequestBytes(String url) throws IOException {
		String USER_AGENT = "Mozilla/5.0";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		InputStream is = con.getInputStream();
		ZipInputStream zipStream = new ZipInputStream(is);

		ZipEntry zipEntry = zipStream.getNextEntry();
		byte[] bytes = zipStream.readAllBytes();
		
		return bytes;
	}
	
	public static void main(String args[]) throws IOException {
		String url = "https://www.nseindia.com/content/historical/DERIVATIVES/2019/AUG/fo02AUG2019bhav.csv.zip";
		url = "https://kitecharts-aws.zerodha.com" + "/api/chart/SYMBOL/INTERVAL?"
				+ "public_token=px7RlNQX3W7rg9Vm2WOUDbGzo6WBxUqy&" + "user_id=YF3210&oi=1" + "&api_key=kitefront&"
				+ "access_token=&" + "from=2019-09-01" + "&to=TODATE&" + "ciqrandom=1564301727399";
		url = url.replace("SYMBOL", "INFY");
		url = url.replace("TODATE", "2020-01-28");
		makeGetRequest(url);
	}
}
