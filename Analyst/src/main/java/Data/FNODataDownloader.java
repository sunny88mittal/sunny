package Data;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

import Constants.FileConstants;

public class FNODataDownloader {

	private static final String DATE = "DATE";

	private static final String MONTH = "MONTH";

	private static final String YEAR = "YEAR";

	private static final String FNO_URL = "https://www.nseindia.com/content/historical/DERIVATIVES/YEAR/MONTH/foDATEMONTHYEARbhav.csv.zip";

	public static void updateFNOData() throws IOException {
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

		String url = FNO_URL.replaceAll(DATE, dateString).replaceAll(MONTH, month + "").replaceAll(YEAR,
				"" + yearValue);

		getFNOData(url);
	}

	private static void getFNOData(String url) throws IOException {
		String[] tokens = url.split("/");
		String fileName = tokens[tokens.length - 1];

		String fileLocation = FileConstants.FNO_BASE_PATH + fileName;
		if (!IOHelper.isDataAlreadyUpdated(fileLocation.replace(".zip", ""))) {
			byte[] data = NetworkHelper.makeGetRequestBytes(url);
			IOHelper.writeToZipFile(fileLocation, data);
			IOHelper.extractZipFile(fileLocation);
			System.out.println("Data updated for : " + fileName);
		} else {
			System.out.println("Data already updated for : " + fileName);
		}
	}

	private static void getPastFNOData(int days) throws InterruptedException {
		LocalDateTime date = LocalDateTime.now();
		for (int i = 0; i < days; i++) {
			date = date.minusDays(1);
			if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
				continue;
			}
			int dateValue = date.getDayOfMonth();
			int yearValue = date.getYear();

			String month = date.getMonth().toString();
			month = month.subSequence(0, 3).toString().toUpperCase();

			String dateString = dateValue + "";
			if (dateValue < 10) {
				dateString = "0" + dateValue;
			}

			String url = FNO_URL.replaceAll(DATE, dateString).replaceAll(MONTH, month + "").replaceAll(YEAR,
					"" + yearValue);
			try {
				getFNOData(url);
			} catch (IOException e) {
				System.out.println("Error for : " + url);
				Thread.sleep(1000);
			}
		}
	}

	public static void main(String args[]) throws IOException, InterruptedException {
		getPastFNOData(620);
	}
}
