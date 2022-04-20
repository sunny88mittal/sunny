package OptionsData;

import java.io.File;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
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

	private static class OptionsDataInternal {

		public Map<String, List<OptionsChain>> optionChainsMap = new HashMap<String, List<OptionsChain>>();

		public Map<String, TimeSeries> timesSeriesMap = new HashMap<String, TimeSeries>();

		public Map<String, List<OptionsChainInterpretation>> optionsChainInterpretationsMap = new HashMap<String, List<OptionsChainInterpretation>>();

		public Map<String, ClosePriceIndicator> closePriceIndicatorMap = new HashMap<String, ClosePriceIndicator>();
	}

	private static Map<String, OptionsDataInternal> optionsDataMap = new HashMap<String, OptionsDataInternal>();

	static {
		// Instruments
		instruments.add("NIFTY");
		instruments.add("BANKNIFTY");
	}

	private Set<String> filesAlreadyRead = new HashSet<String>();
	private Set<String> timeAlreadyRead = new HashSet<String>();

	public List<OptionsChainInterpretation> getOptionschainInterpretations(String instrument, String date) {
		return optionsDataMap.get(date).optionsChainInterpretationsMap.get(instrument);
	}

	public List<OptionsChain> getOptionsChainTimeSeries(String instrument, String date) {
		return optionsDataMap.get(date).optionChainsMap.get(instrument);
	}

	public OptionsChain getLatestOptionsChain(String instrument, String date) {
		Map<String, List<OptionsChain>> optionChainsMap = optionsDataMap.get(date).optionChainsMap;
		return optionChainsMap.get(instrument).get(optionChainsMap.get(instrument).size() - 1);
	}

	public synchronized void updateData(String date) {
		if (optionsDataMap.get(date) == null) {
			initializeOptionsDataInternalForDate(date);
		}

		for (String instrument : instruments) {
			String dirLocation = FileConstants.OPTIONS_FILE_BASE_PATH + "\\" + date + "\\" + instrument;
			for (File file : IOHelper.getFilesInDir(dirLocation)) {
				Date fileDate = new Date(file.lastModified());
				String fileTime = fileDate.getYear() + "-" +  fileDate.getMonth() + "-" + fileDate.getDate() + "-"  + fileDate.getHours() + "-" + fileDate.getMinutes();
				if (!filesAlreadyRead.contains(file.getAbsolutePath()) && !timeAlreadyRead.contains(fileTime)) {
					try {
						String fileContents = IOHelper.readFile(file.getAbsolutePath());

						long lastModifiedTime = file.lastModified();

						OptionsChain optionsChain = NewOptionsChainBuilder.getOptionsChain(fileContents,
								lastModifiedTime);

						List<OptionsChain> optionsChainList = optionsDataMap.get(date).optionChainsMap.get(instrument);

						optionsChainList.add(optionsChain);

						// Interpret Options Chain
						OptionsChainInterpreter.interpretOptionsChain(optionsChain);

						// Update time series
						updateTimeSeries(instrument, optionsChain, date);

						// Update interpretations
						updateInterpretations(instrument, optionsChain, date);

					} catch (Exception ex) {
						System.out.println(ex);
					}

					filesAlreadyRead.add(file.getAbsolutePath());
					timeAlreadyRead.add(fileTime);
				}
			}
		}
	}

	private void initializeOptionsDataInternalForDate(String date) {
		OptionsDataInternal optionsDataInternal = new OptionsDataInternal();
		for (String instrument : instruments) {
			optionsDataInternal.optionChainsMap.put(instrument, new ArrayList<OptionsChain>());

			TimeSeries timeSeries = new BaseTimeSeries.SeriesBuilder().withName(instrument).build();
			optionsDataInternal.timesSeriesMap.put(instrument, timeSeries);

			ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(timeSeries);
			optionsDataInternal.closePriceIndicatorMap.put(instrument, closePriceIndicator);

			List<OptionsChainInterpretation> optionsChainInterpretations = new ArrayList<OptionsChainInterpretation>();
			optionsDataInternal.optionsChainInterpretationsMap.put(instrument, optionsChainInterpretations);
		}
		optionsDataMap.put(date, optionsDataInternal);
	}

	private void updateTimeSeries(String instrument, OptionsChain optionsChain, String date) {
		if (optionsChain != null) {
			double pcr = optionsChain.putOI / optionsChain.callOI;
			ZonedDateTime zdt = Instant.ofEpochMilli(optionsChain.timeStamp).atZone(ZoneId.systemDefault());
			TimeSeries series = optionsDataMap.get(date).timesSeriesMap.get(instrument);
			series.addBar(zdt, 0, 0, 0, pcr);
		}
	}

	private void updateInterpretations(String instrument, OptionsChain optionsChain, String date) {
		ClosePriceIndicator closePriceIndicator = optionsDataMap.get(date).closePriceIndicatorMap.get(instrument);
		TimeSeries series = optionsDataMap.get(date).timesSeriesMap.get(instrument);
		List<OptionsChainInterpretation> optionsChainInterpretations = optionsDataMap
				.get(date).optionsChainInterpretationsMap.get(instrument);

		EMAIndicator shortEMA = new EMAIndicator(closePriceIndicator, 7);
		EMAIndicator longEMA = new EMAIndicator(closePriceIndicator, 17);

		LocalTime barDate = series.getLastBar().getEndTime().toLocalTime();
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
		optionsChainInterpretation.time = barDate;
		optionsChainInterpretations.add(optionsChainInterpretation);
	}
}
