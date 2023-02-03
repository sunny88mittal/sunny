package Indicators;

//https://github.com/arkochhar/Technical-Indicators/blob/master/indicator/indicators.py
//https://github.com/techietrader/Trading-indicators-and-Chart-patterns/blob/master/Indicators/Super_Trend.py

import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.ATRIndicator;
import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.adx.MinusDIIndicator;
import org.ta4j.core.indicators.adx.PlusDIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.PrecisionNum;

import Constants.CandleStickInterval;
import Constants.StockSymbols;
import DataUtil.DataUtil;

public class SuperTrendIndicator extends CachedIndicator<Num> {

	private static final long serialVersionUID = 940675151428473388L;

	private int period;

	private int multiplier;

	private ATRIndicator atrIndicator;

	public SuperTrendIndicator(TimeSeries series, int period, int multiplier) {
		super(series);
		this.period = period;
		this.multiplier = multiplier;
		atrIndicator = new ATRIndicator(series, period);
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

	public static void main(String args[]) {
		TimeSeries timeSeries = DataUtil.getTimeSeries(StockSymbols.BANKNIFTY.name, CandleStickInterval.MINUTE_5);
		ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(timeSeries);

		SuperTrendIndicator stIndicator = new SuperTrendIndicator(timeSeries, 10, 2);
		PlusDIIndicator plusDIIndicator = new PlusDIIndicator(timeSeries, 14);
		MinusDIIndicator minusDIIndicator = new MinusDIIndicator(timeSeries, 14);
		RSIIndicator rsiIndicator = new RSIIndicator(closePriceIndicator, 14);

		int length = timeSeries.getBarCount();
		String previousSignal = null;
		for (int i = 200; i < length; i++) {
			if (stIndicator.getValue(i).intValue() < timeSeries.getBar(i).getClosePrice().intValue()
					&& rsiIndicator.getValue(i).intValue() > 50
					&& plusDIIndicator.getValue(i).intValue() > minusDIIndicator.getValue(i).intValue()
					&& (previousSignal == null || previousSignal.equals("SELL")) ) {
				System.out.println(i + " " + timeSeries.getBar(i).getDateName() + " "
						+ timeSeries.getBar(i).getClosePrice() + ":" + "BUY");
				previousSignal = "BUY";
			}

			if (stIndicator.getValue(i).intValue() > timeSeries.getBar(i).getClosePrice().intValue()
					&& rsiIndicator.getValue(i).intValue() < 50
					&& plusDIIndicator.getValue(i).intValue() < minusDIIndicator.getValue(i).intValue()
					&& (previousSignal == null || previousSignal.equals("BUY"))) {
				System.out.println(i + " " + timeSeries.getBar(i).getDateName() + " "
						+ timeSeries.getBar(i).getClosePrice() + ":" + "SELL");
				previousSignal = "SELL";
			}
		}
	}
}
