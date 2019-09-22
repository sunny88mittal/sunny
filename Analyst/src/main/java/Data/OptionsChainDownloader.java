package Data;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import Entities.OptionsChain;
import Entities.OptionsDataRow;

public class OptionsChainDownloader {

	private static long lastModifiedTime;

	private static String url = "https://www.nseindia.com/live_market/dynaContent/live_watch/option_chain/optionKeys.jsp?symbolCode=-10006&symbol=BANKNIFTY&symbol=BANKNIFTY&instrument=OPTIDX&date=26SEP2019&segmentLink=17&symbolCount=2&segmentLink=17";

	public static OptionsChain getOptionsChain() throws IOException, InterruptedException {
		String rawData = NetworkHelper.makeGetRequest(url);
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
			callRow.optionType = "CALL";
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
			putRow.optionType = "PUT";
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

	public static void main(String args[]) throws IOException, InterruptedException {
		TimeSeries series = new BaseTimeSeries.SeriesBuilder().withName("ABC").build();
		ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);
		int lastMinAnalyzed = -1;
		while (true) {
			Date date = new Date(System.currentTimeMillis());
			int currentMin = date.getMinutes();
			if (currentMin % 3 == 0 && currentMin != lastMinAnalyzed) {
				lastMinAnalyzed = currentMin;
				OptionsChain optionsChain = getOptionsChain();
				if (optionsChain == null) {
					continue;
				}

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
			Thread.sleep(20 * 1000);
		}
	}
}
