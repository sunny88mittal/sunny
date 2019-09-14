package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Constants.StockSymbols;
import Entities.OptionsChain;

public class OptionsChainDownloader {

	private static String getLatestData() throws FileNotFoundException {
		String fileLocation = "C:\\Users\\sunmitta\\Desktop\\Perosnal\\Stocks\\Data\\Live Options Chain\\05-09-2019\\AutoSave_1567655526225.htm";
		String fnoHtml = "";
		Scanner scanner = new Scanner(new File(fileLocation));
		while (scanner.hasNextLine()) {
			fnoHtml += scanner.nextLine();
		}
		scanner.close();
		return fnoHtml;
	}

	public static OptionsChain getOptionsChain(String symbol, String date) throws IOException {
		String rawData = getLatestData();
		Document doc = Jsoup.parse(rawData);
		OptionsChain optionsChain = getOptionsChain(doc);
		return optionsChain;
	}

	private static OptionsChain getOptionsChain(Document doc) {
		OptionsChain optionsChain = new OptionsChain();

		// Get the name of the underlying
		String underlying = doc.getElementsContainingText("Underlying Index").last().child(0).textNodes().get(0)
				.toString();
		String name = underlying.split(" ")[0];

		Element table = doc.getElementsByClass("opttbldata").get(0).getElementById("octable");
		Elements chainElements = table.child(1).children();
		/*
		 * Prepare call and put lists List<OptionsDataRow> callOptions = new
		 * ArrayList<>(); List<OptionsDataRow> putOptions = new ArrayList<>(); for (int
		 * i = 0; i < chainElements.size() - 1; i++) { Element row =
		 * chainElements.get(i); Elements columns = row.children();
		 * 
		 * // Strike String strike =
		 * columns.get(11).child(0).child(0).textNodes().get(0).text().trim();
		 * 
		 * // Call Data int start = 0; String callOI =
		 * getTextFromNormalColumn(columns.get(++start)); String callChangeInOI =
		 * getTextFromNormalColumn(columns.get(++start)); String callVolume =
		 * getTextFromNormalColumn(columns.get(++start)); String callIV =
		 * getTextFromNormalColumn(columns.get(++start)); String callPrice =
		 * getTextFromHyperLinkColumn(columns.get(++start)); String callNetChange =
		 * getTextFromNormalColumn(columns.get(++start)); String callBidQty =
		 * getTextFromNormalColumn(columns.get(++start)); String callBidPrice =
		 * getTextFromNormalColumn(columns.get(++start)); String callAskPrice =
		 * getTextFromNormalColumn(columns.get(++start)); String callAskQty =
		 * getTextFromNormalColumn(columns.get(++start));
		 * 
		 * // Put Data start = 22; String putOI =
		 * getTextFromNormalColumn(columns.get(--start)); String putChangeInOI =
		 * getTextFromNormalColumn(columns.get(--start)); String putVolume =
		 * getTextFromNormalColumn(columns.get(--start)); String putIV =
		 * getTextFromNormalColumn(columns.get(--start)); String putPrice =
		 * getTextFromHyperLinkColumn(columns.get(--start)); String putNetChange =
		 * getTextFromNormalColumn(columns.get(--start)); String putAskQty =
		 * getTextFromNormalColumn(columns.get(--start)); String putAskPrice =
		 * getTextFromNormalColumn(columns.get(--start)); String putBidPrice =
		 * getTextFromNormalColumn(columns.get(--start)); String putBidQty =
		 * getTextFromNormalColumn(columns.get(--start)); }
		 */

		// Get total OI for Call and put
		Element totalOIRow = chainElements.get(chainElements.size() - 1);
		Elements columns = totalOIRow.children();
		String callOI = getTextFromNormalColumn(columns.get(1).child(0)).replace(",", "");
		String callOIVol = getTextFromNormalColumn(columns.get(3).child(0)).replace(",", "");
		String putOI = getTextFromNormalColumn(columns.get(7).child(0)).replace(",", "");
		String putOIVol = getTextFromNormalColumn(columns.get(5).child(0)).replace(",", "");

		// Prepare the options chain object
		optionsChain.symbol = name;
		optionsChain.callOI = Integer.parseInt(callOI);
		optionsChain.callOIVol = Integer.parseInt(callOIVol);
		optionsChain.putOI = Integer.parseInt(putOI);
		optionsChain.putOIVol = Integer.parseInt(putOIVol);
		return optionsChain;
	}

	private static String getTextFromNormalColumn(Element column) {
		return column.textNodes().get(0).text().trim();
	}

	private static String getTextFromHyperLinkColumn(Element column) {
		String value = column.textNodes().get(0).text().trim();
		if (value.equals("")) {
			value = column.child(0).textNodes().get(0).text().trim();
		}
		return value;
	}

	public static void main(String args[]) throws IOException {
		OptionsChain optionsChain = getOptionsChain(StockSymbols.BANKNIFTY.name, "1AUG2019");
		System.out.println("PCR is : " + optionsChain.putOI / optionsChain.callOI);
	}
}
