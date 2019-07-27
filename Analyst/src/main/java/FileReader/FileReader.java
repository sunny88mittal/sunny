package FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.ta4j.core.TimeSeries;

import com.google.gson.Gson;

import Entities.CandleStickData;
import Entities.DataFile;

public class FileReader {

	public static List<CandleStickData> getCandeStickData(String fileLocation) {
		List<CandleStickData> candleStickDataList = new ArrayList<>();
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

		Gson gson = new Gson();
		DataFile dataFile = gson.fromJson(line, DataFile.class);
		List<List<String>> candles = dataFile.getData().getCandles();
		for (List<String> candle : candles) {
			String timestamp = candle.get(0);
			float open = Float.parseFloat(candle.get(1));
			float high = Float.parseFloat(candle.get(2));
			float low = Float.parseFloat(candle.get(3));
			float close = Float.parseFloat(candle.get(4));
			float volume = Float.parseFloat(candle.get(5));
			CandleStickData candleStickData = new CandleStickData(timestamp, open, high, low, close, volume);
			candleStickDataList.add(candleStickData);
		}

		return candleStickDataList;
	}

	public static void main(String args[]) {
		String file = "C:\\Users\\sunmitta\\Desktop\\Perosnal\\Stocks\\Data\\BAJFINANCE\\3minute.json";
		List<CandleStickData> candleStickDataList = getCandeStickData(file);
		System.out.println(candleStickDataList.get(10));
		TimeSeries series = FileReaderUtil.convertToTimeseries(candleStickDataList);
		System.out.println(series.getBarCount());
	}
}
