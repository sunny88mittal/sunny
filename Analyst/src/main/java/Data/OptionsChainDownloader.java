package Data;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import Constants.FileConstants;
import Constants.TradeConstants;
import Entities.OptionsChain;
import Entities.OptionsChainInterpretation;

public class OptionsChainDownloader {

	private static long lastModifiedTime = 0;

	private static String SYMBOL = "BANKNIFTY";

	private static String CUR_DATE = "3OCT2019";

	private static String NEXT_DATE = "10OCT2019";

	private static String CUR_URL = URLConstants.OPTIONS_CHAIN_URL.replace("SYMBOL", SYMBOL).replace("DATE", CUR_DATE);

	private static String NEXT_URL = URLConstants.OPTIONS_CHAIN_URL.replace("SYMBOL", SYMBOL).replace("DATE",
			NEXT_DATE);

	private static String DATA_FILE_NAME = "OPTIONSDATA";

	private static TimeSeries series = new BaseTimeSeries.SeriesBuilder().withName("ABC").build();

	private static List<OptionsChainInterpretation> optionsChainInterpretations = new ArrayList<OptionsChainInterpretation>();

	private static ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);

	private static void loadDataFromDisk() {
		// We are restarting, load the data till now from disk
		if (lastModifiedTime == 0) {
			String dateFolder = getFolderName(LocalDateTime.now());
			for (File file : IOHelper.getFilesInDir(FileConstants.OPTIONS_FILE_BASE_PATH, dateFolder)) {
				String fileContents = IOHelper.readFile(file.getAbsolutePath());
				lastModifiedTime = file.lastModified();
				OptionsChain optionsChain = OptionsChainBuilder.getOptionsChain(fileContents, lastModifiedTime);
				updateTimeSeries(optionsChain);
				updateInterpretations(optionsChain);
				printLatestInterpretation();
			}
		}
	}

	public static List<OptionsChainInterpretation> getOptionschainInterpretations() {
		return optionsChainInterpretations;
	}

	public static OptionsChain getOptionsChain() throws IOException, InterruptedException {
		// Load data from disk if we are starting again
		loadDataFromDisk();

		// Get data from network
		String rawData = NetworkHelper.makeGetRequest(CUR_URL);
		if (rawData.isEmpty()) {
			return null;
		}

		// Update last modified time
		lastModifiedTime = System.currentTimeMillis();

		// Write the data to disk
		writeToDisk(rawData, LocalDateTime.now());

		// Get Options Chain
		OptionsChain optionsChain = OptionsChainBuilder.getOptionsChain(rawData, lastModifiedTime);

		// Interpret Options Chain
		OptionsChainInterpreter.interpretOptionsChain(optionsChain);

		// Update time series
		updateTimeSeries(optionsChain);

		// Update interpretations
		updateInterpretations(optionsChain);

		// Prepare Data For Next Day
		prepareDataForNextDay();

		return optionsChain;
	}

	private static void prepareDataForNextDay() throws IOException {
		LocalDateTime twoThirtyPM = LocalDateTime.now().withHour(14).withMinute(30);
		LocalDateTime timeNow = LocalDateTime.now();
		String rawData = "";

		// Get raw data for next day
		if (timeNow.isAfter(twoThirtyPM)) {
			if (timeNow.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
				rawData = NetworkHelper.makeGetRequest(NEXT_URL);
			} else {
				rawData = NetworkHelper.makeGetRequest(CUR_URL);
			}
		}

		// Get next trading day
		LocalDateTime nextTradingDay = timeNow.plusDays(1);
		if (nextTradingDay.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
			nextTradingDay = timeNow.plusDays(2);
		}
		writeToDisk(rawData, nextTradingDay);
	}

	private static String getFolderName(LocalDateTime date) {
		String pattern = "dd-MM-yyyy";
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		return date.format(dateFormatter);
	}

	private static void writeToDisk(String data, LocalDateTime date) throws IOException {
		String dateFolder = getFolderName(date);
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

	private static void updateInterpretations(OptionsChain optionsChain) {
		EMAIndicator shortEMA = new EMAIndicator(closePriceIndicator, 5);
		EMAIndicator longEMA = new EMAIndicator(closePriceIndicator, 13);

		LocalTime date = series.getLastBar().getEndTime().toLocalTime();
		double pcr = optionsChain.putOI / optionsChain.callOI;

		String signal = "";
		if (shortEMA.getValue(series.getEndIndex()).isGreaterThan(longEMA.getValue(series.getEndIndex()))) {
			signal = TradeConstants.BUY;
		} else if (shortEMA.getValue(series.getEndIndex()).isLessThan(longEMA.getValue(series.getEndIndex()))) {
			signal = TradeConstants.SELL;
		} else {
			signal = TradeConstants.HOLD;
		}

		OptionsChainInterpretation optionsChainInterpretation = new OptionsChainInterpretation();
		optionsChainInterpretation.shortEMA = shortEMA.getValue(series.getEndIndex()).doubleValue();
		optionsChainInterpretation.longEMA = longEMA.getValue(series.getEndIndex()).doubleValue();
		optionsChainInterpretation.signal = signal;
		optionsChainInterpretation.pcr = pcr;
		optionsChainInterpretation.time = date;
		optionsChainInterpretations.add(optionsChainInterpretation);
	}

	private static void printLatestInterpretation() {
		OptionsChainInterpretation optionsChainInterpretation = optionsChainInterpretations
				.get(optionsChainInterpretations.size() - 1);
		System.out.println(optionsChainInterpretation.time + " " + optionsChainInterpretation.signal + " "
				+ optionsChainInterpretation.pcr);
	}

	public static void main(String args[]) throws IOException, InterruptedException {
		// Real time analysis
		LocalDateTime nineTenAM = LocalDateTime.now().withHour(9).withMinute(10);
		LocalDateTime threeThirtyThreePM = LocalDateTime.now().withHour(15).withMinute(33);
		int lastMinAnalyzed = -1;
		while (true) {
			try {
				LocalDateTime now = LocalDateTime.now();
				DayOfWeek dayToday = now.getDayOfWeek();
				int currentMin = now.getMinute();
				if (currentMin % 3 == 0 && currentMin != lastMinAnalyzed) {
					lastMinAnalyzed = currentMin;
					if (now.isAfter(nineTenAM) && now.isBefore(threeThirtyThreePM)
							&& !(dayToday.equals(DayOfWeek.SATURDAY) || dayToday.equals(DayOfWeek.SUNDAY))) {
						getOptionsChain();
						printLatestInterpretation();
					} else {
						// System.out.println("Market Closed");
					}
				}
				Thread.sleep(10 * 1000);
			} catch (Exception ex) {
				System.out.println("Some Error");
			}
		}
	}
}
