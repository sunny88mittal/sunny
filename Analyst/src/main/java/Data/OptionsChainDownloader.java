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
import java.util.List;

import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import Constants.FileConstants;
import Constants.NSEHolidays;
import Constants.TradeConstants;
import Entities.OptionsChain;
import Entities.OptionsChainInterpretation;

public class OptionsChainDownloader {

	public static enum EXPIRY {
		WEEKLY, MONTHLY
	}

	private final String symbol;

	private String CUR_URL;

	private String NEXT_URL;

	private LocalDateTime CUR_DATE;

	private LocalDateTime NEXT_DATE;

	private EXPIRY expiry;

	private long lastModifiedTime = 0;

	private static String DATA_FILE_NAME = "OPTIONSDATA";

	private TimeSeries series = new BaseTimeSeries.SeriesBuilder().withName("ABC").build();

	private List<OptionsChainInterpretation> optionsChainInterpretations = new ArrayList<OptionsChainInterpretation>();

	private ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);

	private List<OptionsChain> optionsChainList = new ArrayList<OptionsChain>();

	public OptionsChainDownloader(String symbol, EXPIRY expiry) {
		this.symbol = symbol;
		this.expiry = expiry;
		updateOptionsURLs();
	}

	public List<OptionsChainInterpretation> getOptionschainInterpretations() {
		return optionsChainInterpretations;
	}

	public OptionsChain getLatestOptionsChain() throws IOException, InterruptedException {
		loadDataFromDisk();
		return optionsChainList.get(optionsChainList.size() - 1);
	}
	
	public List<OptionsChain> getOptionsChainTimeSeries() {
		loadDataFromDisk();
		return optionsChainList;
	}

	public void updateOptionsURLs() {
		LocalDateTime date = LocalDateTime.now();

		// Weekly Expiry Get immediately next Thursday
		while (date.getDayOfWeek() != DayOfWeek.THURSDAY) {
			date = date.plusDays(1);
		}
		CUR_DATE = date;
		NEXT_DATE = date.plusWeeks(1);

		// Monthly Expiry
		if (expiry == EXPIRY.MONTHLY) {
			// Get last Thursday of current month
			while (date.plusWeeks(1).getMonth() == LocalDateTime.now().getMonth()) {
				date = date.plusWeeks(1);
			}

			// If todays date is after the last Thursday of this month
			if (LocalDateTime.now().getDayOfMonth() > date.getDayOfMonth()) {
				// Get the last Thursday of next month
				while (date.plusWeeks(1).getMonth() == LocalDateTime.now().plusMonths(1).getMonth()) {
					date = date.plusWeeks(1);
				}
			}
			CUR_DATE = date;
			NEXT_DATE = date.plusMonths(1);
		}

		CUR_URL = URLConstants.OPTIONS_CHAIN_URL.replace("SYMBOL", symbol).replace("DATE",
				getExpiryDateAsString(CUR_DATE));
		NEXT_URL = URLConstants.OPTIONS_CHAIN_URL.replace("SYMBOL", symbol).replace("DATE",
				getExpiryDateAsString(NEXT_DATE));
	}

	private static String getExpiryDateAsString(LocalDateTime date) {
		String expiryString = "";
		expiryString += date.getDayOfMonth();
		expiryString += date.getMonth().toString().substring(0, 3).toUpperCase();
		expiryString += date.getYear();
		return expiryString;
	}

	public void loadPreviousDataFromDisk() {
		LocalDateTime now = LocalDateTime.now();
		if (!NSEHolidays.isHoliday(now)) {
			series = new BaseTimeSeries.SeriesBuilder().withName("ABC").build();
			optionsChainInterpretations = new ArrayList<OptionsChainInterpretation>();
			closePriceIndicator = new ClosePriceIndicator(series);
			optionsChainList = new ArrayList<OptionsChain>();
			lastModifiedTime = 0;
			loadDataFromDisk();
		}
	}

	public void updateOptionsData() throws IOException, InterruptedException {
		// Load data from disk if we are starting again
		loadDataFromDisk();

		// Get data from network
		String rawData = NetworkHelper.makeGetRequest(CUR_URL);
		if (rawData.isEmpty()) {
			return;
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

		optionsChainList.add(optionsChain);
	}

	private synchronized void loadDataFromDisk() {
		// We are restarting, load the data till now from disk
		if (lastModifiedTime == 0) {
			String dateFolder = getFolderName(LocalDateTime.now());
			String dirLocation = FileConstants.OPTIONS_FILE_BASE_PATH + "\\" + dateFolder + "\\" + symbol;
			for (File file : IOHelper.getFilesInDir(dirLocation)) {
				String fileContents = IOHelper.readFile(file.getAbsolutePath());

				lastModifiedTime = file.lastModified();

				OptionsChain optionsChain = OptionsChainBuilder.getOptionsChain(fileContents, lastModifiedTime);

				optionsChainList.add(optionsChain);

				// Interpret Options Chain
				OptionsChainInterpreter.interpretOptionsChain(optionsChain);

				// Update time series
				updateTimeSeries(optionsChain);

				// Update interpretations
				updateInterpretations(optionsChain);
			}
		}
	}

	private void prepareDataForNextDay() throws IOException {
		LocalDateTime twoThirtyPM = LocalDateTime.now().withHour(14).withMinute(30);
		LocalDateTime timeNow = LocalDateTime.now();

		// Get raw data for next day
		if (timeNow.isAfter(twoThirtyPM)) {
			// Get URL to use
			String urlToUse = timeNow.getDayOfMonth() == CUR_DATE.getDayOfMonth() ? NEXT_URL : CUR_URL;

			// Get Data
			String rawData = NetworkHelper.makeGetRequest(urlToUse);

			// Get next trading day
			LocalDateTime nextTradingDay = timeNow.plusDays(1);
			if (nextTradingDay.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
				nextTradingDay = nextTradingDay.plusDays(2);
			}
			while (NSEHolidays.isHoliday(nextTradingDay)) {
				nextTradingDay = nextTradingDay.plusDays(1);
			}

			// Write to disk
			writeToDisk(rawData, nextTradingDay);
		}
	}

	private String getFolderName(LocalDateTime date) {
		String pattern = "dd-MM-yyyy";
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		return date.format(dateFormatter);
	}

	private void writeToDisk(String data, LocalDateTime date) throws IOException {
		String dateFolder = getFolderName(date);

		// Create folder for date if not there
		String folderLocation = FileConstants.OPTIONS_FILE_BASE_PATH;
		IOHelper.createDirIfReq(folderLocation, dateFolder);

		// Create folder for stock if not there
		folderLocation = folderLocation + "\\" + dateFolder;
		IOHelper.createDirIfReq(folderLocation, symbol);

		// Final folder location
		folderLocation = folderLocation + "\\" + symbol;

		// File location
		String timestamp = System.currentTimeMillis() + "";
		String fileName = folderLocation + "\\" + DATA_FILE_NAME + "_" + timestamp + ".html";

		// Write to disk
		IOHelper.writeToFile(fileName, data);
	}

	private boolean updateTimeSeries(OptionsChain optionsChain) {
		if (optionsChain == null) {
			return false;
		}
		double pcr = optionsChain.putOI / optionsChain.callOI;
		ZonedDateTime zdt = Instant.ofEpochMilli(optionsChain.timeStamp).atZone(ZoneId.systemDefault());
		series.addBar(zdt, 0, 0, 0, pcr);
		return true;
	}

	private void updateInterpretations(OptionsChain optionsChain) {
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

	public void printLatestInterpretation() {
		OptionsChainInterpretation optionsChainInterpretation = optionsChainInterpretations
				.get(optionsChainInterpretations.size() - 1);
		System.out.println(optionsChainInterpretation.time + " " + optionsChainInterpretation.signal + " "
				+ optionsChainInterpretation.pcr);
	}

	public static void main(String args[]) throws IOException, InterruptedException {
		LocalDateTime date = LocalDateTime.now();
		System.out.println(date.getDayOfMonth());
		System.out.println(date.getMonth().toString().substring(0, 3).toUpperCase());
		System.out.println(date.getYear());
	}
}
