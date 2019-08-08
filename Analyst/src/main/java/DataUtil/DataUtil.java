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
			CandleStickData candleStickData = new CandleStickData(timestamp, open, high, low, close, volume);
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
}
