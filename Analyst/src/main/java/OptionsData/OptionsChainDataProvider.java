package OptionsData;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import Constants.FileConstants;
import Constants.TradeConstants;
import Entities.OptionsChain;
import Entities.OptionsChainInterpretation;
import Helper.IOHelper;

@Component
public class OptionsChainDataProvider {

	private static List<String> instruments = new ArrayList<String>();

	private static Map<String, List<OptionsChain>> optionChainsMap = new HashMap<String, List<OptionsChain>>();

	private static Map<String, TimeSeries> timesSeriesMap = new HashMap<String, TimeSeries>();

	private static Map<String, List<OptionsChainInterpretation>> optionsChainInterpretationsMap = new HashMap<String, List<OptionsChainInterpretation>>();

	private static Map<String, ClosePriceIndicator> closePriceIndicatorMap = new HashMap<String, ClosePriceIndicator>();

	static {
		// Instruments
		instruments.add("NIFTY");
		instruments.add("BANKNIFTY");

		// Initialize maps
		for (String instrument : instruments) {
			optionChainsMap.put(instrument, new ArrayList<OptionsChain>());

			TimeSeries timeSeries = new BaseTimeSeries.SeriesBuilder().withName(instrument).build();
			timesSeriesMap.put(instrument, timeSeries);

			ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(timeSeries);
			closePriceIndicatorMap.put(instrument, closePriceIndicator);

			List<OptionsChainInterpretation> optionsChainInterpretations = new ArrayList<OptionsChainInterpretation>();
			optionsChainInterpretationsMap.put(instrument, optionsChainInterpretations);
		}
	}

	private Set<String> filesAlreadyRead = new HashSet<String>();

	public List<OptionsChainInterpretation> getOptionschainInterpretations(String instrument) {
		return optionsChainInterpretationsMap.get(instrument);
	}

	public List<OptionsChain> getOptionsChainTimeSeries(String instrument) {
		return optionChainsMap.get(instrument);
	}

	public OptionsChain getLatestOptionsChain(String instrument) {
		return optionChainsMap.get(instrument).get(optionChainsMap.get(instrument).size() - 1);
	}

	public synchronized void updateData() {
		String dateFolder = getFolderName(LocalDateTime.now());

		for (String instrument : instruments) {
			String dirLocation = FileConstants.OPTIONS_FILE_BASE_PATH + "\\" + dateFolder + "\\" + instrument;
			for (File file : IOHelper.getFilesInDir(dirLocation)) {
				if (!filesAlreadyRead.contains(file.getAbsolutePath())) {
					try {
						String fileContents = IOHelper.readFile(file.getAbsolutePath());

						long lastModifiedTime = file.lastModified();

						OptionsChain optionsChain = NewOptionsChainBuilder.getOptionsChain(fileContents, lastModifiedTime);

						List<OptionsChain> optionsChainList = optionChainsMap.get(instrument);
						if (optionsChainList == null) {
							optionsChainList = new ArrayList<OptionsChain>();
							optionChainsMap.put(instrument, optionsChainList);
						}

						optionsChainList.add(optionsChain);

						// Interpret Options Chain
						OptionsChainInterpreter.interpretOptionsChain(optionsChain);

						// Update time series
						updateTimeSeries(instrument, optionsChain);

						// Update interpretations
						updateInterpretations(instrument, optionsChain);

					} catch (Exception ex) {
						System.out.println(ex);
					}

					filesAlreadyRead.add(file.getAbsolutePath());
				}
			}
		}
	}

	private void updateTimeSeries(String instrument, OptionsChain optionsChain) {
		if (optionsChain != null) {
			double pcr = optionsChain.putOI / optionsChain.callOI;
			ZonedDateTime zdt = Instant.ofEpochMilli(optionsChain.timeStamp).atZone(ZoneId.systemDefault());
			TimeSeries series = timesSeriesMap.get(instrument);
			series.addBar(zdt, 0, 0, 0, pcr);
		}
	}

	private void updateInterpretations(String instrument, OptionsChain optionsChain) {
		ClosePriceIndicator closePriceIndicator = closePriceIndicatorMap.get(instrument);
		TimeSeries series = timesSeriesMap.get(instrument);
		List<OptionsChainInterpretation> optionsChainInterpretations = optionsChainInterpretationsMap.get(instrument);

		EMAIndicator shortEMA = new EMAIndicator(closePriceIndicator, 7);
		EMAIndicator longEMA = new EMAIndicator(closePriceIndicator, 17);

		LocalTime date = series.getLastBar().getEndTime().toLocalTime();
		double pcr = optionsChain.putOI / optionsChain.callOI;

		String signal = "";
		if (shortEMA.getValue(series.getEndIndex()).isGreaterThan(longEMA.getValue(series.getEndIndex()))) {
			signal = TradeConstants.BUY;
		} else if (shortEMA.getValue(series.getEndIndex()).isLessThan(longEMA.getValue(series.getEndIndex()))) {
			signal = TradeConstants.SELL;
		} else {
			signal = TradeConstants.HOLD;
		}

		OptionsChainInterpretation optionsChainInterpretation = new OptionsChainInterpretation();
		optionsChainInterpretation.shortEMA = shortEMA.getValue(series.getEndIndex()).doubleValue();
		optionsChainInterpretation.longEMA = longEMA.getValue(series.getEndIndex()).doubleValue();
		optionsChainInterpretation.signal = signal;
		optionsChainInterpretation.pcr = pcr;
		optionsChainInterpretation.time = date;
		optionsChainInterpretations.add(optionsChainInterpretation);
	}

	private String getFolderName(LocalDateTime date) {
		String pattern = "dd-MM-yyyy";
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		return date.format(dateFormatter);
	}
}
