package File;

import Constants.FileConstants;

public class FileReaderUtil {

	public static String getFileLocation(String stock, String candleStickInterval) {
		String fileLocation = FileConstants.DATA_FILE_BASE_PATH + "//" + stock + "//" + candleStickInterval + ".json";
		return fileLocation;
	}
}
