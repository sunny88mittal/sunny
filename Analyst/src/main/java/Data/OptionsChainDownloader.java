package Data;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import Entities.OptionsChain;

public class OptionsChainDownloader {

	private static long lastModifiedTime;

	private static String url = "https://www.nseindia.com/live_market/dynaContent/live_watch/option_chain/optionKeys.jsp?symbolCode=-10006&symbol=NIFTY&symbol=NIFTY&instrument=OPTIDX&date=19SEP2019&segmentLink=17&symbolCount=2&segmentLink=17";

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

				float pcr = optionsChain.putOI / optionsChain.callOI;
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
