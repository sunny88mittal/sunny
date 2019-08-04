package Strategy;

import org.ta4j.core.TimeSeries;

import File.FileReader;
import File.FileReaderUtil;

public class StrategyUtil {

	public static TimeSeries getTimeSeries(String stock, String interval) {
		String fileLocation = FileReaderUtil.getFileLocation(stock, interval);
		TimeSeries timeSeries = FileReaderUtil.convertToTimeseries(FileReader.getCandeStickData(fileLocation));
		return timeSeries;
	}
}
