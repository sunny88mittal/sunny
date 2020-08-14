package File;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Constants.FileConstants;

public class FileReader {

	private static Map<String, String> candleStickCache = new HashMap<String, String>();
	private static Map<LocalDate, List<String>> fnoCache = new HashMap<LocalDate, List<String>>();

	public static String getCandeStickData(String fileLocation) {
		if (candleStickCache.containsKey(fileLocation)) {
			return candleStickCache.get(fileLocation);
		}

		String line = Helper.IOHelper.readFile(fileLocation);

		candleStickCache.put(fileLocation, line);
		return line;
	}

	public static List<String> getFNOData(LocalDate date) throws FileNotFoundException {
		if (fnoCache.containsKey(date)) {
			return fnoCache.get(date);
		}

		int dateValue = date.getDayOfMonth();
		int yearValue = date.getYear();
		String month = date.getMonth().toString();
		month = month.subSequence(0, 3).toString().toUpperCase();
		String dateString = dateValue + "";
		if (dateValue < 10) {
			dateString = "0" + dateValue;
		}

		String fileName = FileConstants.FNO_FILE_FORMAT.replaceAll("DATE", dateString)
				.replaceAll("YEAR", "" + yearValue).replaceAll("MONTH", month);
		String fileLocation = FileConstants.FNO_BASE_PATH + fileName;

		List<String> fnoData = getCSVData(fileLocation);
		fnoCache.put(date, fnoData);

		return fnoData;
	}

	public static List<String> getCSVData(String fileLocation) throws FileNotFoundException {
		List<String> fnoData = new ArrayList<String>();
		Scanner scanner = new Scanner(new File(fileLocation));
		while (scanner.hasNextLine()) {
			fnoData.add(scanner.nextLine());
		}
		scanner.close();

		return fnoData;
	}

	public static void main(String args[]) throws FileNotFoundException {

		/*
		 * String file =
		 * "C:\\Users\\sunmitta\\Desktop\\Perosnal\\Stocks\\Data\\BAJFINANCE\\3minute.json";
		 * List<CandleStickData> candleStickDataList = DataUtil.getCandeStickData(file);
		 * System.out.println(candleStickDataList.get(10)); TimeSeries series =
		 * DataUtil.convertToTimeseries(candleStickDataList);
		 * System.out.println(series.getBarCount());
		 */

		getCSVData("C:\\Users\\sunmitta\\Desktop\\Perosnal\\Stocks\\FnoData\\fo16AUG2019bhav.csv");
	}
}
