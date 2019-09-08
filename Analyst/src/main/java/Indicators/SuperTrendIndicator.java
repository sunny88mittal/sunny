package Indicators;

//https://github.com/arkochhar/Technical-Indicators/blob/master/indicator/indicators.py
//https://github.com/techietrader/Trading-indicators-and-Chart-patterns/blob/master/Indicators/Super_Trend.py

import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.num.Num;

import Constants.CandleStickInterval;
import Constants.StockSymbols;
import DataUtil.DataUtil;

public class SuperTrendIndicator extends CachedIndicator<Num> {

	private static final long serialVersionUID = 940675151428473388L;

	private SuperTrendUpperBandIndicator upperBandIndicator;

	private SuperTrendLowerBandIndicator lowerBandIndicator;

	public SuperTrendIndicator(TimeSeries series, int period, int multiplier) {
		super(series);
		upperBandIndicator = new SuperTrendUpperBandIndicator(series, period, multiplier);
		lowerBandIndicator = new SuperTrendLowerBandIndicator(series, period, multiplier);
	}

	@Override
	protected Num calculate(int index) {
		Num close = this.getTimeSeries().getBar(index).getClosePrice();
		Num upperBand = upperBandIndicator.getValue(index);
		Num lowerBand = lowerBandIndicator.getValue(index);

		Num superTrend = lowerBand;
		if (close.isLessThanOrEqual(upperBand)) {
			superTrend = upperBand;
		}

		return superTrend;
	}

	public static void main(String args[]) {
		TimeSeries timeSeries = DataUtil.getTimeSeries(StockSymbols.AXISBANK.name, CandleStickInterval.DAY);
		SuperTrendIndicator indicator = new SuperTrendIndicator(timeSeries, 7, 3);
		int length = timeSeries.getBarCount();
		for (int i = 0; i < length; i++) {
			System.out.println(timeSeries.getBar(i).getDateName() + " " + indicator.getValue(i).intValue());
		}
	}
}
