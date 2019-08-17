package Data;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

import Constants.FileConstants;

public class FNODataDownloader {

	private static final String DATE = "DATE";

	private static final String MONTH = "MONTH";

	private static final String YEAR = "YEAR";

	private static final String FNO_URL = "https://www.nseindia.com/content/historical/DERIVATIVES/2019/MONTH/foDATEMONTHYEARbhav.csv.zip";

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

		String url = FNO_URL.replaceAll(DATE, dateValue + "").replaceAll(MONTH, month + "").replaceAll(YEAR,
				"" + yearValue);

		String[] tokens = url.split("/");
		String fileName = tokens[tokens.length - 1];

		String fileLocation = FileConstants.FNO_BASE_PATH + fileName;
		if (!IOHelper.isDataAlreadyUpdated(fileLocation.replace(".zip", ""))) {
			byte[] data = NetworkHelper.makeGetRequestBytes(url);
			IOHelper.writeToZipFile(fileLocation, data);
			System.out.println("Data updated for : " + fileName);
		} else {
			System.out.println("Data already updated for : " + fileName);
		}
	}

	public static void main(String args[]) throws IOException {
		updateFNOData();
	}
}
