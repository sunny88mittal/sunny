package File;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;

import Constants.FileConstants;
import Entities.CandleStickData;

public class FileReaderUtil {

	public static String getFileLocation(String stock, String candleStickInterval) {
		String fileLocation = FileConstants.FILE_BASE_PATH + "//" + stock + "//" + candleStickInterval + ".json";
		return fileLocation;
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
