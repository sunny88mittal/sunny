package Data;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

import Constants.FileConstants;

public class PastDataDownloader {

	private static final String DATE = "DATE";

	private static final String MONTH = "MONTH";

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
			byte[] data = NetworkHelper.makeGetRequestBytes(url);
			IOHelper.writeToFile(fileLocation, data);
			System.out.println("Data updated for : " + fileName);
		} else {
			System.out.println("Data already updated for : " + fileName);
		}
	}

	private static void getPastData(int days, String urlFormat, String writeLocationBase) throws InterruptedException {
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

			String url = urlFormat.replaceAll(DATE, dateString).replaceAll(MONTH, month + "").replaceAll(YEAR,
					"" + yearValue);
			try {
				getData(url, writeLocationBase);
			} catch (IOException e) {
				System.out.println("Error for : " + url);
				Thread.sleep(5000);
			}
		}
	}

	public static void main(String args[]) throws IOException, InterruptedException {
		getPastData(770, URLConstants.FNO_URL, FileConstants.FNO_BASE_PATH);
		getPastData(770, URLConstants.STOCKS_URL, FileConstants.STOCKS_DATA_FILE_BASE_PATH);
	}
}
