package File;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.ta4j.core.TimeSeries;

import DataUtil.DataUtil;
import Entities.CandleStickData;

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

	public static void main(String args[]) {
		String file = "C:\\Users\\sunmitta\\Desktop\\Perosnal\\Stocks\\Data\\BAJFINANCE\\3minute.json";
		List<CandleStickData> candleStickDataList = DataUtil.getCandeStickData(file);
		System.out.println(candleStickDataList.get(10));
		TimeSeries series = DataUtil.convertToTimeseries(candleStickDataList);
		System.out.println(series.getBarCount());
	}
}
