package File;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Constants.FileConstants;

public class FileReader {

	public static String getCandeStickData(String fileLocation) {
		String line = "";
		try {
			Scanner scanner = new Scanner(new File(fileLocation));
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				break;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return line;
	}

	public static List<String> getFNOData(String fileLocation) {
		List<String> fnoData = new ArrayList<String>();

		if (fileLocation == null) {
			LocalDateTime date = LocalDateTime.now();

			int dateValue = date.getDayOfMonth();
			if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
				dateValue -= 1;
			} else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
				dateValue -= 2;
			}

			int yearValue = date.getYear();

			String month = date.getMonth().toString();
			month = month.subSequence(0, 3).toString().toUpperCase();
			
			String dateString = dateValue + "";
			if (dateValue < 10) {
				dateString = "0" + dateValue;
			}

			String fileName = FileConstants.FNO_FILE_FORMAT.replaceAll("DATE", dateString)
					.replaceAll("YEAR", "" + yearValue).replaceAll("MONTH", month);
			fileLocation = FileConstants.FNO_BASE_PATH + fileName;
		}

		try {
			Scanner scanner = new Scanner(new File(fileLocation));
			while (scanner.hasNextLine()) {
				fnoData.add(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return fnoData;
	}

	public static void main(String args[]) {
		/*String file = "C:\\Users\\sunmitta\\Desktop\\Perosnal\\Stocks\\Data\\BAJFINANCE\\3minute.json";
		List<CandleStickData> candleStickDataList = DataUtil.getCandeStickData(file);
		System.out.println(candleStickDataList.get(10));
		TimeSeries series = DataUtil.convertToTimeseries(candleStickDataList);
		System.out.println(series.getBarCount());*/
		
		getFNOData("C:\\Users\\sunmitta\\Desktop\\Perosnal\\Stocks\\FnoData\\fo16AUG2019bhav.csv");
	}
}
