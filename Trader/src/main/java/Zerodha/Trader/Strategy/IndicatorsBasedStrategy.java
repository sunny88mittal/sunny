package Zerodha.Trader.Strategy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

//https://github.com/arkochhar/Technical-Indicators/blob/master/indicator/indicators.py
//https://github.com/techietrader/Trading-indicators-and-Chart-patterns/blob/master/Indicators/Super_Trend.py

import org.ta4j.core.Bar;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.ATRIndicator;
import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.adx.MinusDIIndicator;
import org.ta4j.core.indicators.adx.PlusDIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.PrecisionNum;

import Zerodha.Trader.Core.AppConstants;

public class IndicatorsBasedStrategy extends CachedIndicator<Num> {

	private static final long serialVersionUID = 940675151428473388L;

	private int period;

	private int multiplier;

	private ATRIndicator atrIndicator;

	private TimeSeries series;

	private String signal = null;

	private Set<String> barTimestamps = new HashSet<String>();

	private Set<String> signalTimestamps = new HashSet<String>();

	private OHLC currentCandle = null;

	private class OHLC {
		float open;

		float high;

		float low;

		float close;
	}

	public IndicatorsBasedStrategy(TimeSeries series, int period, int multiplier) {
		super(series);
		this.series = series;
		this.period = period;
		this.multiplier = multiplier;
		atrIndicator = new ATRIndicator(series, period);
	}

	public void createOrUpdateBar(float price) {
		if (currentCandle == null) {
			currentCandle = new OHLC();
		}

		if (currentCandle.open == 0) {
			currentCandle.open = price;
		}

		if (currentCandle.high == 0 || price > currentCandle.high) {
			currentCandle.high = price;
		}

		if (currentCandle.low == 0 || price < currentCandle.low) {
			currentCandle.low = price;
		}

		currentCandle.close = price;
	}

	public void flushBar(String time) {
		if (currentCandle != null && !barTimestamps.contains(time)) {
			barTimestamps.add(time);
			float[] ohlc = new float[4];
			ohlc[0] = currentCandle.open;
			ohlc[1] = currentCandle.high;
			ohlc[2] = currentCandle.low;
			ohlc[3] = currentCandle.close;
			addBar(time, ohlc, true);
			currentCandle = null;
		}
	}

	public void loadData() {
		try (BufferedReader br = new BufferedReader(new FileReader(AppConstants.BANKNIFTY_PRICES_FILE))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				String values[] = line.split(",");
				float[] ohlc = new float[4];
				ohlc[0] = Float.parseFloat(values[0]);
				ohlc[1] = Float.parseFloat(values[1]);
				ohlc[2] = Float.parseFloat(values[2]);
				ohlc[3] = Float.parseFloat(values[3]);
				addBar(i + "", ohlc, false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addBar(String time, float[] ohlc, boolean writeData) {
		ZonedDateTime barTime = ZonedDateTime.now();
		Number open = (Number) ohlc[0];
		Number high = (Number) ohlc[1];
		Number low = (Number) ohlc[2];
		Number close = (Number) ohlc[3];
		Number volume = (Number) 0;
		series.addBar(barTime, open, high, low, close, volume);

		if (writeData) {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(AppConstants.BANKNIFTY_PRICES_FILE, true))) {
				bw.newLine();
				String data = ohlc[0] + "," + ohlc[1] + "," + ohlc[2] + "," + ohlc[3];
				bw.write(data + "");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected Num calculate(int index) {
		int count = period * multiplier * 10;
		int seriesStartIndex = index - count;

		// Generate ATR values
		Num atrValues[] = new Num[count];
		for (int i = 0; i < count; i++) {
			atrValues[i] = atrIndicator.getValue(seriesStartIndex + i);
		}

		// Basic Bands
		Num upperBasic[] = new Num[count];
		Num lowerBasic[] = new Num[count];
		for (int i = 0; i < count; i++) {
			Num twoAsNum = PrecisionNum.valueOf(2);
			Num muliplier = PrecisionNum.valueOf(multiplier);
			Bar currentBar = this.getTimeSeries().getBar(seriesStartIndex + i);

			Num low = currentBar.getMinPrice();
			Num high = currentBar.getMaxPrice();
			upperBasic[i] = low.plus(high).dividedBy(twoAsNum).plus(muliplier.multipliedBy(atrValues[i]));
			lowerBasic[i] = low.plus(high).dividedBy(twoAsNum).minus(muliplier.multipliedBy(atrValues[i]));
		}

		// Lower and Upper Band
		Num upper[] = new Num[count];
		Num lower[] = new Num[count];

		// Upper
		upper[0] = upperBasic[0];
		for (int i = 1; i < count; i++) {
			Bar currentBar = this.getTimeSeries().getBar(seriesStartIndex + (i - 1));
			Num close = currentBar.getClosePrice();
			if (close.isLessThanOrEqual(upper[i - 1])) {
				upper[i] = upper[i - 1].min(upperBasic[i]);
			} else {
				upper[i] = upperBasic[i];
			}
		}

		// Lower
		lower[0] = lowerBasic[0];
		for (int i = 1; i < count; i++) {
			Bar currentBar = this.getTimeSeries().getBar(seriesStartIndex + (i - 1));
			Num close = currentBar.getClosePrice();
			if (close.isGreaterThanOrEqual(lower[i - 1])) {
				lower[i] = lower[i - 1].max(lowerBasic[i]);
			} else {
				lower[i] = lowerBasic[i];
			}
		}

		// SuperTrend
		Num superTrend[] = new Num[count];
		Bar currentBar1 = this.getTimeSeries().getBar(seriesStartIndex);
		Num close1 = currentBar1.getClosePrice();
		if (close1.isLessThanOrEqual(upper[0])) {
			superTrend[0] = upper[0];
		} else {
			superTrend[0] = lower[0];
		}

		for (int i = 1; i < count; i++) {
			Bar currentBar = this.getTimeSeries().getBar(seriesStartIndex + (i));
			Num close = currentBar.getClosePrice();
			if (superTrend[i - 1].isEqual(upper[i - 1]) && close.isLessThanOrEqual(upper[i])) {
				superTrend[i] = upper[i];
			} else if (superTrend[i - 1].isEqual(upper[i - 1]) && close.isGreaterThanOrEqual(upper[i])) {
				superTrend[i] = lower[i];
			} else if (superTrend[i - 1].isEqual(lower[i - 1]) && close.isGreaterThanOrEqual(lower[i])) {
				superTrend[i] = lower[i];
			} else if (superTrend[i - 1].isEqual(lower[i - 1]) && close.isLessThanOrEqual(lower[i])) {
				superTrend[i] = upper[i];
			}
		}

		return superTrend[count - 1];
	}

	public String getSignal(String time) {
		if (!signalTimestamps.contains(time)) {
			signalTimestamps.add(time);
			return getSignal();
		}
		return null;
	}

	public String getSignal() {
		IndicatorsBasedStrategy stIndicator = new IndicatorsBasedStrategy(series, 10, 2);
		PlusDIIndicator plusDIIndicator = new PlusDIIndicator(series, 14);
		MinusDIIndicator minusDIIndicator = new MinusDIIndicator(series, 14);
		ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);
		RSIIndicator rsiIndicator = new RSIIndicator(closePriceIndicator, 14);

		int length = series.getBarCount() - 1;
		Bar bar = series.getBar(length);

		if (stIndicator.getValue(length).intValue() < bar.getClosePrice().intValue()
				&& rsiIndicator.getValue(length).intValue() > 50
				&& plusDIIndicator.getValue(length).intValue() > minusDIIndicator.getValue(length).intValue()
				&& (signal == null || signal.equals("SELL"))) {
			signal = "BUY";
			return signal;
		}

		if (stIndicator.getValue(length).intValue() > bar.getClosePrice().intValue()
				&& rsiIndicator.getValue(length).intValue() < 50
				&& plusDIIndicator.getValue(length).intValue() < minusDIIndicator.getValue(length).intValue()
				&& (signal == null || signal.equals("BUY"))) {
			signal = "SELL";
			return signal;
		}

		return null;
	}

	public static void main(String args[]) {
		TimeSeries series = new BaseTimeSeries.SeriesBuilder().withName("ABC").build();
		IndicatorsBasedStrategy strategy = new IndicatorsBasedStrategy(series, 10, 2);
		strategy.loadData();

		try (BufferedReader br = new BufferedReader(new FileReader(AppConstants.BANKNIFTY_TEST_DATA_FILE))) {
			String line;
			int i = 1000;
			while ((line = br.readLine()) != null) {
				String values[] = line.split(",");
				float[] ohlc = new float[4];
				ohlc[0] = Float.parseFloat(values[0]);
				ohlc[1] = Float.parseFloat(values[1]);
				ohlc[2] = Float.parseFloat(values[2]);
				ohlc[3] = Float.parseFloat(values[3]);
				strategy.addBar(i + "", ohlc, true);
				String signal = strategy.getSignal();
				if (signal != null) {
					System.out.println(signal + ": at : " + ohlc[3]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
