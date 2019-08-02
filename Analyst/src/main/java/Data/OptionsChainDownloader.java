package Data;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import Constants.StockSymbols;
import Entities.OptionsChain;

public class OptionsChainDownloader {

	private static final String SYMBOL = "SYMBOL";

	private static final String DATE = "DATE";

	private static final String URL = "https://www.nseindia.com/live_market/dynaContent/live_watch/option_chain/optionKeys.jsp?"
			+ "&instrument=OPTIDX" + "&symbol=" + SYMBOL + "+&date=" + DATE;

	private static String getDataFor(String symbol, String date) throws IOException {
		String url = URL.replace(SYMBOL, symbol).replace(DATE, date);
		String data = NetworkHelper.makeGetRequest(url);
		return data;
	}

	public static OptionsChain getOptionsChain(String symbol, String date) throws IOException {
		String rawData = getDataFor(symbol, date);
		Document doc = Jsoup.parse(rawData);
		Element table = doc.getElementsByClass("opttbldata").get(0).getElementById("octable");
		OptionsChain optionsChain = new OptionsChain();
		return optionsChain;
	}

	public static void main(String args[]) throws IOException {
		getOptionsChain(StockSymbols.BANK_NIFTY.name, "1AUG2019");
	}
}
