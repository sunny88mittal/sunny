package Data;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.lang3.time.DateUtils;

public class IOHelper {

	public static void writeToFile(String fileLocation, String data) throws IOException {
		FileWriter fileWriter = new FileWriter(fileLocation);
		fileWriter.write(data);
		fileWriter.flush();
		fileWriter.close();
	}

	public static void writeToZipFile(String fileLocation, byte[] data) throws IOException {
		FileOutputStream out = new FileOutputStream(fileLocation);
		out.write(data, 0, data.length);
		out.flush();
		out.close();
	}

	public static void extractZipFile(String fileLocation) throws IOException {
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(fileLocation));
		ZipEntry entry = zipIn.getNextEntry();
		if (entry != null) {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileLocation.replace(".zip", "")));
			bos.write(zipIn.readAllBytes());
			bos.flush();
			bos.close();
			zipIn.close();
		}
		
		File file = new File(fileLocation);
		file.delete();
	}

	public static void createDirIfReq(String basePath, String dirName) {
		String fileLocation = basePath + "\\" + dirName;
		File file = new File(fileLocation);
		if (!file.exists()) {
			file.mkdir();
		}
	}

	public static boolean isDataAlreadyUpdated(String fileLocation) {
		File file = new File(fileLocation);
		long lastModfiedTime = file.lastModified();
		return DateUtils.isSameDay(new Date(), new Date(lastModfiedTime));
	}
}
