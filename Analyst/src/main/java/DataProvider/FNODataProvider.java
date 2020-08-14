package DataProvider;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

import DataUtil.DataUtil;
import Entities.FNOData;
import File.FileReader;

public class FNODataProvider {

	public static List<FNOData> getFNOData(LocalDate date) throws FileNotFoundException {
		List<String> rawData = FileReader.getFNOData(date);
		return DataUtil.convertToFNOData(rawData);
	}
}
