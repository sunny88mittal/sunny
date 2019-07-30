package Data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IOHelper {

	public static void writeToFile(String fileLocation, String data) throws IOException {
		FileWriter fileWriter = new FileWriter(fileLocation);
		fileWriter.write(data);
		fileWriter.flush();
		fileWriter.close();
	}

	public static void createDirIfReq(String basePath, String dirName) {
		String fileLocation = basePath + "\\" + dirName;
		File file = new File(fileLocation);
		if (!file.exists()) {
			file.mkdir();
		}
	}
}
