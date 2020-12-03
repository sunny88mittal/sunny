package DataProvider;

import java.io.FileNotFoundException;
import java.util.List;

import File.FileReader;

public class StocksDataSegregator {

	public static void ingestFile(String fileLocation) throws FileNotFoundException {
		List<String> data = FileReader.getCSVData(fileLocation);
		for (String str : data) {
			String items[] = str.split(",");
			if (items[1].equals("EQ")) {
				String stockName = items[0];
				float open = Float.parseFloat(items[2]);
				float high = Float.parseFloat(items[3]);
				float low = Float.parseFloat(items[4]);
				float close = Float.parseFloat(items[5]);
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		String fileLocation = "C:\\Users\\sunmitta\\Desktop\\Perosnal\\Stocks\\Data\\StocksRawData\\cm22MAY2020bhav.csv";
		ingestFile(fileLocation);
	}
}
