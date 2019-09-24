package Data;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import Constants.FileConstants;
import Entities.OptionsChain;

public class OptionsChainDownloader {

	private static long lastModifiedTime = 0;

	private static String url = "https://www.nseindia.com/live_market/dynaContent/live_watch/option_chain/optionKeys.jsp?symbolCode=-10006&symbol=BANKNIFTY&symbol=BANKNIFTY&instrument=OPTIDX&date=26SEP2019&segmentLink=17&symbolCount=2&segmentLink=17";

	private static String DATA_FILE_NAME = "OPTIONSDATA";

	private static TimeSeries series = new BaseTimeSeries.SeriesBuilder().withName("ABC").build();

	private static ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);

	public static OptionsChain getOptionsChain() throws IOException, InterruptedException {
		String rawData = NetworkHelper.makeGetRequest(url);
		if (rawData.isEmpty()) {
			return null;
		}
		lastModifiedTime = System.currentTimeMillis();
		writeToDisk(rawData);
		OptionsChain optionsChain = OptionChainParser.getOptionsChain(rawData, lastModifiedTime);
		return optionsChain;
	}

	private static void writeToDisk(String data) throws IOException {
		String pattern = "dd-MM-YYYY";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateFolder = simpleDateFormat.format(new Date());
		IOHelper.createDirIfReq(FileConstants.OPTIONS_FILE_BASE_PATH, dateFolder);
		String timestamp = System.currentTimeMillis() + "";
		String fileName = FileConstants.OPTIONS_FILE_BASE_PATH + dateFolder + "\\" + DATA_FILE_NAME + "_" + timestamp
				+ ".html";
		IOHelper.writeToFile(fileName, data);
	}

	private static boolean updateTimeSeries(OptionsChain optionsChain) {
		if (optionsChain == null) {
			return false;
		}
		double pcr = optionsChain.putOI / optionsChain.callOI;
		ZonedDateTime zdt = Instant.ofEpochMilli(optionsChain.timeStamp).atZone(ZoneId.systemDefault());
		series.addBar(zdt, 0, 0, 0, pcr);
		return true;
	}

	private static void printUpdate(OptionsChain optionsChain) {
		EMAIndicator shortEMA = new EMAIndicator(closePriceIndicator, 5);
		EMAIndicator longEMA = new EMAIndicator(closePriceIndicator, 13);

		LocalTime date = series.getLastBar().getEndTime().toLocalTime();
		double pcr = optionsChain.putOI / optionsChain.callOI;
		if (shortEMA.getValue(series.getEndIndex()).isGreaterThan(longEMA.getValue(series.getEndIndex()))) {
			System.out.println(date + " BUY" + " " + pcr);
		} else if (shortEMA.getValue(series.getEndIndex()).isLessThan(longEMA.getValue(series.getEndIndex()))) {
			System.out.println(date + " SELL" + " " + pcr);
		} else {
			System.out.println(date + " NO SIGNAL" + " " + pcr);
		}
	}

	public static void main(String args[]) throws IOException, InterruptedException {
		// We are restarting, load the data till now from disk
		if (lastModifiedTime == 0) {
			String pattern = "dd-MM-YYYY";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String dateFolder = simpleDateFormat.format(new Date());
			for (File file : IOHelper.getFilesInDir(FileConstants.OPTIONS_FILE_BASE_PATH, dateFolder)) {
				String fileContents = IOHelper.readFile(file.getAbsolutePath());
				lastModifiedTime = file.lastModified();
				OptionsChain optionsChain = OptionChainParser.getOptionsChain(fileContents, lastModifiedTime);
				updateTimeSeries(optionsChain);
				printUpdate(optionsChain);
			}
		}

		// Real time analysis
		int lastMinAnalyzed = -1;
		while (true) {
			Date date = new Date(System.currentTimeMillis());
			int currentMin = date.getMinutes();
			if (currentMin % 3 == 0 && currentMin != lastMinAnalyzed) {
				lastMinAnalyzed = currentMin;
				OptionsChain optionsChain = getOptionsChain();
				updateTimeSeries(optionsChain);
				printUpdate(optionsChain);
			}
			Thread.sleep(20 * 1000);
		}
	}
}
