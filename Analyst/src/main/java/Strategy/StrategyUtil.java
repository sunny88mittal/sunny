package Strategy;

import org.ta4j.core.TimeSeries;

import DataUtil.DataUtil;
import File.FileReader;
import File.FileReaderUtil;

public class StrategyUtil {

	public static TimeSeries getTimeSeries(String stock, String interval) {
		String fileLocation = FileReaderUtil.getFileLocation(stock, interval);
		TimeSeries timeSeries = DataUtil
				.convertToTimeseries(DataUtil.getCandeStickData(FileReader.getCandeStickData(fileLocation)));
		return timeSeries;
	}
}
