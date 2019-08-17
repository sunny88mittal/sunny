package Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
		return is.readAllBytes();
	}
	
	public static void main(String args[]) throws IOException {
		String url = "https://www.nseindia.com/content/historical/DERIVATIVES/2019/AUG/fo02AUG2019bhav.csv.zip";
		makeGetRequest(url);
	}
}
