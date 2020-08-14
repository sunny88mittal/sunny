package OptionsData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Constants.TradeConstants;
import Entities.OptionsChain;
import Entities.OptionsDataRow;
import Helper.IOHelper;

public class OptionsChainBuilder {

	public static OptionsChain getOptionsChain(String rawData, long lastModifiedTime) {
		Document doc = Jsoup.parse(rawData);

		OptionsChain optionsChain = new OptionsChain();

		// Get the name of the underlying
		String underlying = doc.getElementsMatchingOwnText("Underlying *").last().child(0).textNodes().get(0)
				.toString();
		String name = underlying.split(" ")[0];
		String price = underlying.split(" ")[1];

		Element table = doc.getElementsByClass("opttbldata").get(0).getElementById("octable");
		Elements chainElements = table.child(1).children();

		// Prepare call and put lists
		List<OptionsDataRow> callOptions = new ArrayList<>();
		List<OptionsDataRow> putOptions = new ArrayList<>();
		for (int i = 0; i < chainElements.size() - 1; i++) {
			Element row = chainElements.get(i);
			Elements columns = row.children();

			// Strike
			double strike = Double.parseDouble(columns.get(11).child(0).child(0).textNodes().get(0).text().trim());

			// Call Data
			OptionsDataRow callRow = new OptionsDataRow();
			int start = 0;
			callRow.optionType = TradeConstants.CALL;
			callRow.strikePrice = strike;
			callRow.openInterest = getDoubleFromNormalColumn(columns.get(++start));
			callRow.openInterestChange = getDoubleFromNormalColumn(columns.get(++start));
			callRow.volume = getDoubleFromNormalColumn(columns.get(++start));
			callRow.IV = getDoubleFromNormalColumn(columns.get(++start));
			callRow.LTP = getDoubleFromHyperLinkColumn(columns.get(++start));
			callRow.netChange = getDoubleFromNormalColumn(columns.get(++start));
			callRow.bidQty = getDoubleFromNormalColumn(columns.get(++start));
			callRow.bidPrice = getDoubleFromNormalColumn(columns.get(++start));
			callRow.askPrice = getDoubleFromNormalColumn(columns.get(++start));
			callRow.askQty = getDoubleFromNormalColumn(columns.get(++start));

			// Put Data
			OptionsDataRow putRow = new OptionsDataRow();
			start = 22;
			putRow.optionType = TradeConstants.PUT;
			putRow.strikePrice = strike;
			putRow.openInterest = getDoubleFromNormalColumn(columns.get(--start));
			putRow.openInterestChange = getDoubleFromNormalColumn(columns.get(--start));
			putRow.volume = getDoubleFromNormalColumn(columns.get(--start));
			putRow.IV = getDoubleFromNormalColumn(columns.get(--start));
			putRow.LTP = getDoubleFromHyperLinkColumn(columns.get(--start));
			putRow.netChange = getDoubleFromNormalColumn(columns.get(--start));
			putRow.askQty = getDoubleFromNormalColumn(columns.get(--start));
			putRow.askPrice = getDoubleFromNormalColumn(columns.get(--start));
			putRow.bidPrice = getDoubleFromNormalColumn(columns.get(--start));
			putRow.bidQty = getDoubleFromNormalColumn(columns.get(--start));

			callOptions.add(callRow);
			putOptions.add(putRow);
		}

		// Get total OI for Call and put
		Element totalOIRow = chainElements.get(chainElements.size() - 1);
		Elements columns = totalOIRow.children();
		double callOI = getDoubleFromNormalColumn(columns.get(1).child(0));
		double callOIVol = getDoubleFromNormalColumn(columns.get(3).child(0));
		double putOI = getDoubleFromNormalColumn(columns.get(7).child(0));
		double putOIVol = getDoubleFromNormalColumn(columns.get(5).child(0));

		// Prepare the options chain object
		optionsChain.symbol = name;
		optionsChain.price = price;
		optionsChain.callOI = callOI;
		optionsChain.callOIVol = callOIVol;
		optionsChain.putOI = putOI;
		optionsChain.putOIVol = putOIVol;
		optionsChain.timeStamp = lastModifiedTime;
		optionsChain.callOptions = callOptions;
		optionsChain.putOptions = putOptions;
		return optionsChain;
	}

	private static double getDoubleFromNormalColumn(Element column) {
		double doubleValue = 0;
		String value = column.textNodes().get(0).text().trim().replace(",", "");
		if (!value.equals("-")) {
			doubleValue = Double.parseDouble(value);
		}
		return doubleValue;
	}

	private static double getDoubleFromHyperLinkColumn(Element column) {
		double doubleValue = 0;
		String value = column.textNodes().get(0).text().trim();
		if (value.equals("")) {
			value = column.child(0).textNodes().get(0).text().trim();
		}
		value = value.replace(",", "");
		if (!value.equals("-")) {
			doubleValue = Double.parseDouble(value);
		}
		return doubleValue;
	}

	public static void main(String args[]) throws IOException {
		File file = new File("C:\\Users\\sunmitta\\Desktop\\Perosnal\\Stocks\\Data\\LiveOptionsChain\\25-06-2020\\BANKNIFTY1593057131487.html");
		String fileContents = IOHelper.readFile(file.getAbsolutePath());
		OptionsChain optionsChain = getOptionsChain(fileContents, System.currentTimeMillis());
		System.out.println(optionsChain);
	}
}
