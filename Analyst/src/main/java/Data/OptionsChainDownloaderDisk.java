package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import Constants.StockSymbols;
import Entities.OptionsChain;

public class OptionsChainDownloaderDisk {

	private static int lastScannedFile = -1;

	private static long lastModifiedTime;

	private static Map<String, List<OptionsChain>> optionChainsMap = new HashMap<String, List<OptionsChain>>();

	private static String getLatestData() throws FileNotFoundException, InterruptedException {
		String fnoDirLoc = "C:\\Users\\sunmitta\\Desktop\\Perosnal\\Stocks\\Live Options Chain";
		File fnoDir = new File(fnoDirLoc);
		String fnoHtml = "";
		File[] files = fnoDir.listFiles();
		if (lastScannedFile < (files.length - 1)) {
			++lastScannedFile;
			File file = files[lastScannedFile];
			lastModifiedTime = file.lastModified();
			fnoHtml = "";
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				fnoHtml += scanner.nextLine();
			}
			scanner.close();
		} else {
			Thread.sleep(1000 * 60);
		}
		return fnoHtml;
	}

	public static OptionsChain getOptionsChain() throws IOException, InterruptedException {
		String rawData = getLatestData();
		if (rawData.isEmpty()) {
			return null;
		}
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
		optionsChain.callOIVol = "-".contentEquals(callOIVol) ? 0 : Integer.parseInt(callOIVol);
		optionsChain.putOI = Integer.parseInt(putOI);
		optionsChain.putOIVol = "-".contentEquals(putOIVol) ? 0 : Integer.parseInt(putOIVol);
		optionsChain.timeStamp = lastModifiedTime;
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

	public static void main(String args[]) throws IOException, InterruptedException {
		TimeSeries series = new BaseTimeSeries.SeriesBuilder().withName("ABC").build();
		ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);
		while (true) {
			OptionsChain optionsChain = getOptionsChain();
			if (optionsChain == null) {
				continue;
			}
			Date date = new Date(optionsChain.timeStamp);
			if (date.getMinutes() % 3 == 0) {
				double pcr = optionsChain.putOI / optionsChain.callOI;
				ZonedDateTime zdt = date.toInstant().atZone(ZoneId.systemDefault());
				series.addBar(zdt, 0, 0, 0, pcr);
				EMAIndicator shortEMA = new EMAIndicator(closePriceIndicator, 5);
				EMAIndicator longEMA = new EMAIndicator(closePriceIndicator, 13);
				if (shortEMA.getValue(series.getEndIndex()).isGreaterThan(longEMA.getValue(series.getEndIndex()))) {
					System.out.println(date + " BUY" + " " + pcr);
				} else if (shortEMA.getValue(series.getEndIndex()).isLessThan(longEMA.getValue(series.getEndIndex()))) {
					System.out.println(date + " SELL" + " " + pcr);
				} else {
					System.out.println(date + " NO SIGNAL" + " " + pcr);
				}
			}
		}
	}
}
