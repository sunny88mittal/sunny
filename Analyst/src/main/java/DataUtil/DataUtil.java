package DataUtil;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;

import com.google.gson.Gson;

import Entities.CandleStickData;
import Entities.DataFile;
import Entities.FNOData;
import File.FileReader;
import File.FileReaderUtil;

public class DataUtil {

	public static List<CandleStickData> getCandeStickData(String data) {
		List<CandleStickData> candleStickDataList = new ArrayList<>();

		Gson gson = new Gson();
		DataFile dataFile = gson.fromJson(data, DataFile.class);
		List<List<String>> candles = dataFile.getData().getCandles();
		for (List<String> candle : candles) {
			String timestamp = candle.get(0);
			float open = Float.parseFloat(candle.get(1));
			float high = Float.parseFloat(candle.get(2));
			float low = Float.parseFloat(candle.get(3));
			float close = Float.parseFloat(candle.get(4));
			float volume = Float.parseFloat(candle.get(5));
			float openInterest = Float.parseFloat(candle.get(6));
			CandleStickData candleStickData = new CandleStickData(timestamp, open, high, low, close, volume, openInterest);
			candleStickDataList.add(candleStickData);
		}

		return candleStickDataList;
	}

	public static TimeSeries convertToTimeseries(List<CandleStickData> candleStickDataList) {
		TimeSeries series = new BaseTimeSeries.SeriesBuilder().withName("ABC").build();
		for (CandleStickData candleStickData : candleStickDataList) {
			ZonedDateTime time = ZonedDateTime.parse(candleStickData.timestamp.replace("0530", "05:30"),
					DateTimeFormatter.ISO_OFFSET_DATE_TIME);
			Number open = (Number) candleStickData.open;
			Number close = (Number) candleStickData.close;
			Number low = (Number) candleStickData.low;
			Number high = (Number) candleStickData.high;
			Number volume = (Number) candleStickData.volume;
			series.addBar(time, open, high, low, close, volume);
		}

		return series;
	}

	public static TimeSeries getTimeSeries(String stock, String interval) {
		String fileLocation = FileReaderUtil.getFileLocation(stock, interval);
		TimeSeries timeSeries = DataUtil
				.convertToTimeseries(DataUtil.getCandeStickData(FileReader.getCandeStickData(fileLocation)));
		return timeSeries;
	}

	public static List<FNOData> getFNOData(List<String> data) {
		List<FNOData> fnoData = new ArrayList<FNOData>();

		for (int i = 1; i < data.size(); i++) {
			String tokens[] = data.get(i).split(",");
			FNOData fnoDataEntry = new FNOData();
			fnoDataEntry.INSTRUMENT = tokens[0];
			fnoDataEntry.SYMBOL = tokens[1];
			fnoDataEntry.EXPIRY_DT = tokens[2];
			fnoDataEntry.STRIKE_PR = tokens[3];
			fnoDataEntry.OPTION_TYP = tokens[4];
			fnoDataEntry.OPEN = Float.parseFloat(tokens[5]);
			fnoDataEntry.HIGH = Float.parseFloat(tokens[6]);
			fnoDataEntry.LOW = Float.parseFloat(tokens[7]);
			fnoDataEntry.CLOSE = Float.parseFloat(tokens[8]);
			fnoDataEntry.SETTLE_PR = Float.parseFloat(tokens[9]);
			fnoDataEntry.CONTRACTS = Float.parseFloat(tokens[10]);
			fnoDataEntry.VAL_INLAKH = Float.parseFloat(tokens[11]);
			fnoDataEntry.OPEN_INT = Float.parseFloat(tokens[12]);
			fnoDataEntry.CHG_IN_OI = Float.parseFloat(tokens[13]);
			fnoDataEntry.TIMESTAMP = tokens[14];

			fnoData.add(fnoDataEntry);
		}

		return fnoData;
	}
}
