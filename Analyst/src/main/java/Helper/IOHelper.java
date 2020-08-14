package Helper;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class IOHelper {

	public static void writeToFile(String fileLocation, String data) throws IOException {
		FileWriter fileWriter = new FileWriter(fileLocation);
		fileWriter.write(data);
		fileWriter.flush();
		fileWriter.close();
	}

	public static String readFile(String fileLocation) {
		String finalContent = "";
		try {
			FileReader fr = new FileReader(new File(fileLocation));
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				finalContent += line;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalContent;
	}

	public static void writeToFile(String fileLocation, byte[] data) throws IOException {
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

	public static File[] getFilesInDir(String dirLocation) {
		File file = new File(dirLocation);
		if (file.exists()) {
			return file.listFiles();
		}
		return new File[] {};
	}

	public static void createDirIfReq(String basePath, String dirName) {
		String fileLocation = basePath + "\\" + dirName;
		File file = new File(fileLocation);
		if (!file.exists()) {
			file.mkdir();
		}
	}

	public static boolean fileAlreadyExists(String fileLocation) {
		File file = new File(fileLocation);
		return file.exists();
	}

	public static boolean isDataAlreadyUpdated(String fileLocation) {
		File file = new File(fileLocation);

		long lastModfiedTime = file.lastModified();
		LocalDateTime lastModifiedDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(lastModfiedTime),
				TimeZone.getDefault().toZoneId());

		LocalDate lastMarketDate = LocalDate.now();
		if (lastMarketDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
			lastMarketDate = lastMarketDate.minusDays(1);
		} else if (lastMarketDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
			lastMarketDate = lastMarketDate.minusDays(2);
		}

		return lastModifiedDate.toLocalDate().equals(lastMarketDate);
	}
}
