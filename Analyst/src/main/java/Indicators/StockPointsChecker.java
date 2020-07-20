package Indicators;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Constants.FileConstants;
import Entities.StockPoint;
import File.FileReader;

public class StockPointsChecker {

	public static List<StockPoint> getMatchingStockPoints(LocalDate date, double percentage)
			throws FileNotFoundException {
		return matchStockPoints(getStocksData(date), getStockPointsList(), percentage);
	}

	private static List<StockPoint> matchStockPoints(List<String> stockData, List<StockPoint> stockPoints,
			double percentage) {
		List<StockPoint> stockPointsList = new ArrayList<StockPoint>();
		for (int i = 1; i < stockData.size(); i++) {
			String stockDataEntry = stockData.get(i);
			String tokens[] = stockDataEntry.split(",");
			String symbol = tokens[0];
			double price = Double.parseDouble(tokens[4]);

			for (StockPoint stockPoint : stockPoints) {
				String stockName = stockPoint.stock;
				double value = stockPoint.value;
				if (stockName.equals(symbol) && Math.abs((price - value) * 100 / value) < percentage) {
					stockPointsList.add(stockPoint);
				}
			}
		}

		return stockPointsList;
	}

	private static List<String> getStocksData(LocalDate date) throws FileNotFoundException {
		String dateValue = date.getDayOfMonth() + "";
		dateValue = dateValue.length() == 1 ? "0" + dateValue : dateValue;
		String yearValue = date.getYear() + "";
		String monValue = date.getMonth().name().substring(0, 3);

		String fileLocation = FileConstants.STOCKS_DATA_FILE_NAME.replace("DD", dateValue).replace("MON", monValue)
				.replace("YYYY", yearValue);

		List<String> stocksData = FileReader.getCSVData(fileLocation);
		return stocksData;
	}

	private static List<StockPoint> getStockPointsList() throws FileNotFoundException {
		List<StockPoint> stockPointsList = new ArrayList<StockPoint>();
		List<String> stockPointsData = FileReader.getCSVData(FileConstants.STOCKS_POINTS_FILE);
		for (int i = 1; i < stockPointsData.size(); i++) {
			if (stockPointsData.get(i).equals("")) {
				continue;
			}
			String tokens[] = stockPointsData.get(i).split(",");
			StockPoint stockPointObj = new StockPoint();
			stockPointObj.stock = tokens[0];
			stockPointObj.value = Double.parseDouble(tokens[1]);
			stockPointObj.pointType = tokens[2];
			stockPointsList.add(stockPointObj);
		}
		return stockPointsList;
	}

	public static void main(String args[]) throws FileNotFoundException {
		/*for (int i=0; i< 120; i++) {
			try {
				System.out.println("Checking for :" + LocalDate.now().minusDays(i).toString());
				List<StockPoint> stockPointsList = getMatchingStockPoints(LocalDate.now().minusDays(i), 5);
				for (StockPoint stockPoint : stockPointsList) {
					System.out.println(stockPoint);
				}
				System.out.println();
			} catch (Exception ex) {
				
			}
		}*/
		List<StockPoint> stockPointsList = getMatchingStockPoints(LocalDate.now().minusDays(3), 5);
		for (StockPoint stockPoint : stockPointsList) {
			System.out.println(stockPoint);
		}
	}
}
