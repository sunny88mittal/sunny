package File;

import Constants.FileConstants;

public class FileReaderUtil {

	public static String getFileLocation(String stock, String candleStickInterval) {
		String fileLocation = FileConstants.STOCKS_DATA_ZERODHA_BASE_PATH + "//" + stock + "//" + candleStickInterval + ".json";
		return fileLocation;
	}
}
