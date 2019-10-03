package Analyzer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.ParabolicSarIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import Constants.CandleStickInterval;
import Constants.FileConstants;
import Constants.NSEHolidays;
import Constants.StockSymbols;
import Data.FNODataDownloader;
import Data.StocksDataDownloader;
import DataUtil.DataUtil;
import Entities.DailyAnalysis;
import Entities.ExcelSheet;
import Entities.FNOData;
import File.FileReader;
import File.XLSCreator;
import Indicators.MACDWithSignalIndicator;
import Indicators.SuperTrendIndicator;
import Statistics.StatisticsUtil;

@Component
public class DailyAnalyzer {

	private static int WEEK = 5;

	private static int MONTH = 21;

	public static void main(String args[]) throws Exception {
		runanalyzer();
	}

	@Scheduled(cron = "0 0 21 * * MON-FRI")
	public static void runanalyzer() throws Exception {
		if (NSEHolidays.isHoliday( LocalDateTime.now())) {
			return;
		}
		
		System.out.println("Started at : " + LocalDateTime.now());

		updateData();
		analyze(LocalDate.now());
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
		for (final StockSymbols stockSymbol : StockSymbols.getAllStocksList()) {
			Thread th = new Thread() {
				public void run() {
					try {
						analyzeStock(stockSymbol, 90);
					} catch (IllegalArgumentException | IllegalAccessException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			executor.execute(th);
		}

		while (executor.getActiveCount() > 0) {
			Thread.sleep(1000 * 30);
		}
		executor.shutdown();
		// doPastAnalysis(90);
		System.out.println("Ended at : " + LocalDateTime.now());
	}

	private static void doPastAnalysis(int days) throws InterruptedException {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
		for (int i = 0; i < days; i++) {
			final LocalDate date = LocalDate.now().minusDays(i);
			Thread th = new Thread() {
				public void run() {
					try {
						analyze(date);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
					}
				}
			};
			executor.execute(th);
		}
		while (executor.getActiveCount() > 0) {
			Thread.sleep(1000 * 30);
		}
		executor.shutdown();
	}

	private static void updateData() throws IOException {
		// Update Stocks Data
		StocksDataDownloader.updateDailyDataAllStocks();

		// Update FNO Data
		FNODataDownloader.updateFNOData();
	}

	private static void analyzeStock(StockSymbols stockSymbol, int days)
			throws IllegalArgumentException, IllegalAccessException, IOException {
		List<StockSymbols> list = new ArrayList<StockSymbols>();
		list.add(stockSymbol);

		// Do Analysis
		List<DailyAnalysis> analysis = new ArrayList<DailyAnalysis>();
		for (int i = 0; i < days; i++) {
			LocalDate date = LocalDate.now().minusDays(i);
			try {
				List<DailyAnalysis> dailyAnalysis = analyzeList(list, date);
				for (DailyAnalysis entry : dailyAnalysis) {
					entry.stock = date.toString();
				}
				analysis.addAll(dailyAnalysis);
			} catch (Exception e) {
				// System.out.println("Error:" + date + e.getMessage());
			}
		}

		// Create file location
		String fileLocation = FileConstants.PAST_ANALYSIS_FILE_BASE_PATH + stockSymbol.name;

		// Generate Sheets
		List<ExcelSheet> sheets = new ArrayList<ExcelSheet>();
		sheets.add(generateExcelSheet(analysis, stockSymbol.name));

		// Print the sheet
		XLSCreator.generateXLS(sheets, fileLocation);
		System.out.println("Analysis Created");
	}

	private static void analyze(LocalDate date) throws Exception {
		// Generate Sheets
		List<ExcelSheet> sheets = new ArrayList<ExcelSheet>();
		sheets.add(generateExcelSheet(analyzeList(StockSymbols.getNiftyHeavyStocksList(), date), "NiftyHeavy"));
		sheets.add(generateExcelSheet(analyzeList(StockSymbols.getBankNiftyStocksList(), date), "BankNifty"));
		sheets.add(generateExcelSheet(analyzeList(StockSymbols.getNiftyStocksList(), date), "Nifty"));
		sheets.add(generateExcelSheet(analyzeList(StockSymbols.getAllStocksList(), date), "All"));
		System.out.println("All Stock Lists Analyzed");

		// Create file location
		String fileLocation = FileConstants.DAILY_ANALYSIS_FILE_BASE_PATH + date;

		// Print the sheet
		XLSCreator.generateXLS(sheets, fileLocation);
		System.out.println("Analysis Created");
	}

	private static List<DailyAnalysis> analyzeList(List<StockSymbols> stocksList, LocalDate date) throws Exception {
		List<DailyAnalysis> dailyOutput = new ArrayList<DailyAnalysis>();
		for (StockSymbols stockSymbol : stocksList) {
			dailyOutput.add(checkDaily(stockSymbol, CandleStickInterval.DAY, date));
		}

		return dailyOutput;
	}

	private static ExcelSheet generateExcelSheet(List<DailyAnalysis> dailyOutput, String sheetName)
			throws IllegalArgumentException, IllegalAccessException, IOException {
		// Generate header
		List<String> header = new ArrayList<String>();
		for (Field field : DailyAnalysis.class.getDeclaredFields()) {
			header.add(field.getName().toUpperCase());
		}

		// Collect Data
		List<List<String>> dataRows = new ArrayList<List<String>>();
		for (DailyAnalysis dailyAnalysis : dailyOutput) {
			List<String> dataRow = new ArrayList<String>();
			dataRow.add(dailyAnalysis.stock);
			dataRow.add(dailyAnalysis.closePrice + "");
			dataRow.add(dailyAnalysis.change + "");
			dataRow.add(dailyAnalysis.stsignal);
			dataRow.add(dailyAnalysis.psarsignal);
			dataRow.add(dailyAnalysis.macdsignal);
			dataRow.add(dailyAnalysis.st + "");
			dataRow.add(dailyAnalysis.psar + "");
			dataRow.add(dailyAnalysis.macd + "");
			dataRow.add(dailyAnalysis.monthlyReturn + "");
			dataRow.add(dailyAnalysis.weeklyReturn + "");
			dataRow.add(dailyAnalysis.vol3Months + "");
			dataRow.add(dailyAnalysis.pcr + "");
			dataRow.add(dailyAnalysis.changeInOI + "");
			dataRow.add(dailyAnalysis.maxPain + "");
			dataRows.add(dataRow);
		}

		// Prepare excel sheets
		ExcelSheet sheet = new ExcelSheet(sheetName, header, dataRows);
		return sheet;
	}

	private static DailyAnalysis checkDaily(StockSymbols stockSymbol, String candleStickInterval, LocalDate date)
			throws Exception {
		DailyAnalysis dailyAnalysis = new DailyAnalysis();

		TimeSeries series = DataUtil.getTimeSeries(stockSymbol.name, candleStickInterval);
		ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);

		MACDWithSignalIndicator macd = new MACDWithSignalIndicator(closePriceIndicator);
		ParabolicSarIndicator psar = new ParabolicSarIndicator(series);
		SuperTrendIndicator st = new SuperTrendIndicator(series, 7, 3);

		int backBy = -1;
		for (int i = 0; i < series.getBarCount(); i++) {
			Bar bar = series.getBar(i);
			if (bar.getEndTime().getDayOfMonth() == date.getDayOfMonth()
					&& bar.getEndTime().getMonthValue() == date.getMonthValue()
					&& bar.getEndTime().getYear() == date.getYear()) {
				backBy = series.getBarCount() - (i + 1);
			}
		}

		if (backBy == -1) {
			throw new Exception("No Data found");
		}

		int index = series.getEndIndex() - backBy;
		Bar bar = series.getBar(index);
		int closePrice = bar.getClosePrice().intValue();

		int psarValue = psar.getValue(index).intValue();
		float macdValue = macd.getValue(index).floatValue();
		float stValue = st.getValue(index).floatValue();

		String stSignal = stValue >= closePrice ? "SELL" : "BUY";
		String psarSignal = psarValue >= closePrice ? "SELL" : "BUY";
		String macdSignal = macdValue < 0 ? "SELL" : "BUY";

		List<FNOData> fnoData = DataUtil.getFNOData(FileReader.getFNOData(date));
		fnoData = FNOHelper.removeExpiredEnteries(fnoData, date);

		dailyAnalysis.stock = stockSymbol.name;
		dailyAnalysis.closePrice = closePrice;
		dailyAnalysis.stsignal = stSignal;
		dailyAnalysis.psarsignal = psarSignal;
		dailyAnalysis.macdsignal = macdSignal;
		dailyAnalysis.st = trimDouble(stValue);
		dailyAnalysis.psar = trimDouble(psarValue);
		dailyAnalysis.macd = trimDouble(macdValue);
		dailyAnalysis.change = StatisticsUtil.getReturnsLastNIntervals(series, 1, backBy);
		dailyAnalysis.monthlyReturn = StatisticsUtil.getReturnsLastNIntervals(series, MONTH, backBy);
		dailyAnalysis.weeklyReturn = StatisticsUtil.getReturnsLastNIntervals(series, WEEK, backBy);
		dailyAnalysis.vol3Months = StatisticsUtil.getVolatilityLastNIntervals(series, 3 * MONTH, backBy);
		dailyAnalysis.changeInOI = FNOHelper.getChangeInOI(fnoData, stockSymbol.name);
		dailyAnalysis.pcr = trimDouble(FNOHelper.getPCR(fnoData, stockSymbol.name));
		dailyAnalysis.maxPain = FNOHelper.getMaxPain(fnoData, stockSymbol.name);

		return dailyAnalysis;
	}

	private static double trimDouble(double value) {
		if (Double.isFinite(value)) {
			DecimalFormat df = new DecimalFormat("#.##");
			return Double.parseDouble(df.format(value));
		}
		return value;
	}
}