package Analyzer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.ParabolicSarIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import Constants.CandleStickInterval;
import Constants.FileConstants;
import Constants.StockSymbols;
import Data.StocksDataDownloader;
import DataUtil.DataUtil;
import Entities.DailyAnalysis;
import Entities.ExcelSheet;
import File.XLSCreator;
import Indicators.MACDWithSignalIndicator;
import Statistics.StatisticsUtil;

public class DailyAnalyzer {
	
	private static int WEEK = 5;
	
	private static int MONTH = 21;

	public static void main(String args[]) throws IOException, IllegalArgumentException, IllegalAccessException {
		// Update Stocks Data
		StocksDataDownloader.updateDailyDataAllStocks();
		
		//Generate Sheets
		List<ExcelSheet> sheets = new ArrayList<ExcelSheet>();
		sheets.add(generateExcelSheet(analyzeList(StockSymbols.getNiftyHeavyStocksList()), "Nifty"));
		sheets.add(generateExcelSheet(analyzeList(StockSymbols.getBankNiftyStocksList()), "BankNifty"));
		sheets.add(generateExcelSheet(analyzeList(StockSymbols.getAllStocksList()), "All"));
		
		// Create file location
		LocalDate date = LocalDate.now();
		String fileLocation = FileConstants.DAILY_ANALYSIS_FILE_BASE_PATH + date.toString();

		// Print the sheet
		XLSCreator.generateXLS(sheets, fileLocation);
	}
	
	
	private static List<DailyAnalysis> analyzeList(List<StockSymbols> stocksList)
			throws IOException, IllegalArgumentException, IllegalAccessException {
		List<DailyAnalysis> dailyOutput = new ArrayList<DailyAnalysis>();
		for (StockSymbols stockSymbol : stocksList) {
			dailyOutput.add(checkDaily(stockSymbol, CandleStickInterval.DAY));
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

		//Collect Data
		List<List<String>> dataRows = new ArrayList<List<String>>();
		for (DailyAnalysis dailyAnalysis : dailyOutput) {
			List<String> dataRow = new ArrayList<String>();
			dataRow.add(dailyAnalysis.stock);
			dataRow.add(dailyAnalysis.closePrice + "");
			dataRow.add(dailyAnalysis.signal);
			dataRow.add(dailyAnalysis.psar + "");
			dataRow.add(dailyAnalysis.macd + "");
			dataRow.add(dailyAnalysis.monthlyReturn + "");
			dataRow.add(dailyAnalysis.weeklyReturn + "");
			dataRow.add(dailyAnalysis.vol1Month + "");
			dataRow.add(dailyAnalysis.vol3Months + "");
			dataRow.add(dailyAnalysis.vol6Months + "");
			dataRow.add(dailyAnalysis.volYear + "");
			dataRows.add(dataRow);
		}
		
		//Prepare excel sheets
		ExcelSheet sheet = new ExcelSheet(sheetName, header, dataRows);
		return sheet;
	}

	private static DailyAnalysis checkDaily(StockSymbols stockSymbol, String candleStickInterval) throws IOException {
		int backBy = 9;
		
		DailyAnalysis dailyAnalysis = new DailyAnalysis();
		
		TimeSeries series = DataUtil.getTimeSeries(stockSymbol.name, candleStickInterval);
		ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);

		MACDWithSignalIndicator macd = new MACDWithSignalIndicator(closePriceIndicator);
		ParabolicSarIndicator psar = new ParabolicSarIndicator(series);

		int index = series.getEndIndex() - backBy;
		Bar bar = series.getBar(index);

		int psarValue = psar.getValue(index).intValue();
		float macdValue = macd.getValue(index).floatValue();
		int closePrice = bar.getClosePrice().intValue();
		String signal = "WAIT";

		if ((psarValue < closePrice) && (macdValue > (float) 0.0)) {
			/*System.out.println(stockSymbol.name + " " + bar.getSimpleDateName() + ": Buy " + " Close Price : "
					+ bar.getClosePrice());*/
			signal = "BUY";
		} else if ((psarValue > closePrice) && (macdValue < (float) 0.0)) {
			/*System.out.println(stockSymbol.name + " " + bar.getSimpleDateName() + " : Sell " + " Close Price : "
					+ bar.getClosePrice());*/
			signal = "SELL";
		}
		
		
		dailyAnalysis.stock = stockSymbol.name;
		dailyAnalysis.signal = signal;
		dailyAnalysis.closePrice = closePrice;
		dailyAnalysis.psar = psarValue;
		dailyAnalysis.macd = macdValue;
		dailyAnalysis.monthlyReturn = StatisticsUtil.getReturnsLastNIntervals(series, MONTH, backBy);
		dailyAnalysis.weeklyReturn = StatisticsUtil.getReturnsLastNIntervals(series, WEEK,backBy);
		dailyAnalysis.vol1Month = StatisticsUtil.getVolatilityLastNIntervals(series, MONTH, backBy);
		dailyAnalysis.vol3Months = StatisticsUtil.getVolatilityLastNIntervals(series, 3 * MONTH, backBy);
		dailyAnalysis.vol6Months = StatisticsUtil.getVolatilityLastNIntervals(series, 6 * MONTH, backBy);
		dailyAnalysis.volYear = StatisticsUtil.getVolatilityLastNIntervals(series, 12 * MONTH, backBy);
		
		return dailyAnalysis;
	}
}