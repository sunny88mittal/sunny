package OptionsData;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Constants.FileConstants;
import Entities.OptionsChain;
import Entities.OptionsChainInterpretation;

@Component
public class OptionsChainDataManager {

	@Autowired
	OptionsChainDataProvider optionsChainDataProvider;

	public List<String> getAvailableDates(int noOfDays) {
		String dirLocation = FileConstants.OPTIONS_FILE_BASE_PATH;
		File file = new File(dirLocation);
		List<String> availableDates = Arrays.asList(file.list());
		int size = availableDates.size();

		// Convert it to locat dates and sort
		List<LocalDate> localDates = new ArrayList<LocalDate>();
		for (String dateString : availableDates) {
			String tokens[] = dateString.split("-");
			int date = Integer.parseInt(tokens[0]);
			int month = Integer.parseInt(tokens[1]);
			int year = Integer.parseInt(tokens[2]);
			LocalDate localDate = LocalDate.of(year, month, date);
			localDates.add(localDate);
		}
		Collections.sort(localDates);

		// Get last n days
		localDates = localDates.subList(size - noOfDays, size);
		List<String> dates = new ArrayList<String>();
		for (LocalDate localDate : localDates) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			dates.add(localDate.format(formatter));
		}

		return dates;
	}

	public List<OptionsChainInterpretation> getOptionschainInterpretations(String symbol) {
		optionsChainDataProvider.updateData();
		return optionsChainDataProvider.getOptionschainInterpretations(symbol);
	}

	public OptionsChain getLatestOptionsChain(String symbol) throws IOException, InterruptedException {
		optionsChainDataProvider.updateData();
		return optionsChainDataProvider.getLatestOptionsChain(symbol);
	}

	public List<OptionsChain> getOptionsChainTimeSeries(String symbol) {
		optionsChainDataProvider.updateData();
		return optionsChainDataProvider.getOptionsChainTimeSeries(symbol);
	}
}
