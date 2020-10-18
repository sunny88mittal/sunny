package DataProvider;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import DataUtil.DataUtil;
import Entities.FNOData;
import File.FileReader;

public class FNODataProvider {

	public static List<FNOData> getFNOData(String instrument, String symbol, LocalDate date)
			throws FileNotFoundException {
		List<FNOData> fnoData = getFNOData(date);
		List<FNOData> filteredData = fnoData.stream()
				.filter(e -> e.INSTRUMENT.equals(instrument) && e.SYMBOL.equals(symbol)).collect(Collectors.toList());
		return filteredData;
	}

	public static List<FNOData> getFNOData(LocalDate date) throws FileNotFoundException {
		List<String> rawData = FileReader.getFNOData(date);
		return DataUtil.convertToFNOData(rawData);
	}
}
