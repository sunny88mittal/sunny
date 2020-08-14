package DataProvider;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

import Constants.FileConstants;
import Constants.URLConstants;
import Helper.IOHelper;
import Helper.NetworkHelper;

public class PastDataDownloader {

	private static final String DATE = "DATE";

	private static final String MONTH = "MONTH";
	
	private static final String MONTH1 = "MM";

	private static final String YEAR = "YEAR";

	public static void updateDailyData(String urlFormat, String writeLocationBase) throws IOException {
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

		String url = urlFormat.replaceAll(DATE, dateString).replaceAll(MONTH, month + "").replaceAll(YEAR,
				"" + yearValue);

		getData(url, writeLocationBase);
	}

	private static void getData(String url, String writeLocationBase) throws IOException {
		String[] tokens = url.split("/");
		String fileName = tokens[tokens.length - 1].replace(".zip", "");
		String fileLocation = writeLocationBase + fileName;

		if (!IOHelper.fileAlreadyExists(fileLocation)) {
			if (url.endsWith(".csv")) {
				String data = NetworkHelper.makeGetRequest(url);
				IOHelper.writeToFile(fileLocation, data);
			} else {
				byte[] data = NetworkHelper.makeGetRequestBytes(url);
				IOHelper.writeToFile(fileLocation, data);
			}
			
			System.out.println("Data updated for : " + fileName);
		} else {
			System.out.println("Data already updated for : " + fileName);
		}
	}

	private static void getPastData(int days, String urlFormat, String writeLocationBase) throws InterruptedException {
		for (int i = 0; i < days; i++) {
			LocalDateTime date = LocalDateTime.now();
			date = date.minusDays(i);
			if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
				continue;
			}
			int dateValue = date.getDayOfMonth();
			int yearValue = date.getYear();
			int monthValue = date.getMonthValue();

			String month = date.getMonth().toString();
			month = month.subSequence(0, 3).toString().toUpperCase();

			String dateString = dateValue + "";
			if (dateValue < 10) {
				dateString = "0" + dateValue;
			}
			
			String month1 = "" + monthValue;
			if (monthValue < 10) {
				month1  = "0" + month1;
			}

			String url = urlFormat.replaceAll(DATE, dateString).replaceAll(MONTH, month + "").replaceAll(YEAR,
					"" + yearValue).replaceAll(MONTH1, month1);
			
			try {
				getData(url, writeLocationBase);
			} catch (IOException e) {
				System.out.println("Error for : " + url);
				Thread.sleep(100);
			}
		}
	}

	public static void main(String args[]) throws IOException, InterruptedException {
		getPastData(7, URLConstants.STOCKS_URL, FileConstants.STOCKS_DATA_FILE_BASE_PATH);
		getPastData(7, URLConstants.OI_DATA_URL, FileConstants.OI_BASE_PATH);
		getPastData(7, URLConstants.FNO_URL, FileConstants.FNO_BASE_PATH);
	}
}
