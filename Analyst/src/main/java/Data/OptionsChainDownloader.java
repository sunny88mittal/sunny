package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Constants.StockSymbols;
import Entities.OptionsChain;
import Entities.OptionsDataRow;

public class OptionsChainDownloader {

	private static String getLatestData() throws FileNotFoundException {
		String fileLocation = "C:\\Users\\sunmitta\\Downloads\\AutoSave_1567591216775.htm";
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

		// Prepare call and put lists
		List<OptionsDataRow> callOptions = new ArrayList<>();
		List<OptionsDataRow> putOptions = new ArrayList<>();
		Element table = doc.getElementsByClass("opttbldata").get(0).getElementById("octable");
		Elements chainElements = table.child(1).children();
		for (int i = 0; i < chainElements.size() - 1; i++) {
			Element row = chainElements.get(i);
			Elements columns = row.children();
			String strike = columns.get(11).child(0).child(0).textNodes().get(0).text().trim();
			String callOI = columns.get(1).textNodes().get(0).text().trim();
			//String callPrice = columns.get(5).child(0).textNodes().get(0).text().trim();
			System.out.println(strike);
			System.out.println(callOI);
		}

		// Get total OI for Call and put
		Element totalOIRow = chainElements.get(chainElements.size() - 1);

		// Prepare the options chain object
		optionsChain.symbol = name;
		return optionsChain;
	}

	public static void main(String args[]) throws IOException {
		getOptionsChain(StockSymbols.BANKNIFTY.name, "1AUG2019");
	}
}
