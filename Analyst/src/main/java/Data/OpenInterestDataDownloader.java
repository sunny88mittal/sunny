package Data;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

import Constants.FileConstants;

public class OpenInterestDataDownloader {

	private static final String DATE = "DD";

	private static final String MONTH = "MM";

	private static final String YEAR = "YYYY";
	
	private static void getOIData(String url) throws IOException {
		String[] tokens = url.split("/");
		String fileName = tokens[tokens.length - 1];

		String fileLocation = FileConstants.OI_BASE_PATH + fileName;
		if (!IOHelper.isDataAlreadyUpdated(fileLocation)) {
			byte[] data = NetworkHelper.makeGetRequestBytes(url);
			IOHelper.writeToZipFile(fileLocation, data);
			IOHelper.extractZipFile(fileLocation);
			System.out.println("Data updated for : " + fileName);
		} else {
			System.out.println("Data already updated for : " + fileName);
		}
	}

	private static void getPastOIData(int days) throws InterruptedException {
		LocalDateTime date = LocalDateTime.now();
		for (int i = 0; i < days; i++) {
			date = date.minusDays(1);
			if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
				continue;
			}
			int dateValue = date.getDayOfMonth();
			int yearValue = date.getYear();
			int monthValue = date.getMonthValue();

			String dateString = dateValue + "";
			if (dateValue < 10) {
				dateString = "0" + dateValue;
			} 

			String monthString = monthValue + "";
			if (monthValue < 10) {
				monthString = "0" + monthValue;
			}

			String url = URLConstants.OI_DATA_URL
						.replace(DATE, dateString)
						.replace(MONTH, monthString)
						.replace(YEAR, yearValue + "");
			
			try {
				getOIData(url);
			} catch (IOException e) {
				System.out.println("Error for : " + url);
				Thread.sleep(10);
			}
		}
	}
	
	public static void main(String args[]) throws IOException, InterruptedException {
		getPastOIData(60);
	}
}
