package Indicators;

import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.ATRIndicator;
import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.PrecisionNum;

import Constants.CandleStickInterval;
import Constants.StockSymbols;
import DataUtil.DataUtil;

//https://documentation.chartiq.com/tutorial-Using%20and%20Customizing%20Studies%20-%20Definitions.html#atr-trailing
//https://silo.tips/download/average-true-range-trailing-stops

public class ATRTrailingStopLoss extends CachedIndicator<Num> {

	private static final long serialVersionUID = -8939946592454023602L;

	private int multiplier;

	private TimeSeries series;

	private ATRIndicator atrIndicator;

	public ATRTrailingStopLoss(TimeSeries series, int period, int multiplier) {
		super(series);
		this.multiplier = multiplier;
		this.series = series;
		atrIndicator = new ATRIndicator(series, period);
	}

	@Override
	protected Num calculate(int index) {
		Num[] atrTSLs = new PrecisionNum[series.getBarCount()];
		atrTSLs[0] = PrecisionNum.valueOf(0);

		for (int i = 0; i < 7; i++) {
			Num close = series.getBar(i).getClosePrice();
			Num atr = atrIndicator.getValue(i).multipliedBy(PrecisionNum.valueOf(multiplier));
			atrTSLs[i] = close.minus(atr);
		}

		// Get atrTSLs
		for (int i = 7; i < series.getBarCount(); i++) {
			Num prevClose = series.getBar(i - 1).getClosePrice();
			Num prevATRTSL = atrTSLs[i - 1];

			Num close = series.getBar(i).getClosePrice();
			Num atr = atrIndicator.getValue(i).multipliedBy(PrecisionNum.valueOf(multiplier));
			atrTSLs[i] = prevATRTSL;

			if (close.isLessThan(prevClose)) {
				if (prevATRTSL.isLessThan(close)) { // Down candle in up trend, ATRTSL remains same
					atrTSLs[i] = prevATRTSL;
				} else if (prevATRTSL.isGreaterThan(close)) { // Down candle in down trend, ATRTSL falls, trend changes
					atrTSLs[i] = close.plus(atr);
				}
			} else if (close.isGreaterThan(prevClose)) {
				if (prevATRTSL.isLessThan(close)) { // Up candle in up trend, ATRTSL remains same
					atrTSLs[i] = prevATRTSL;
				} else if (prevATRTSL.isLessThan(close)) { // Up candle in down trend, ATRTSL falls
					atrTSLs[i] = close.minus(atr);
				}
			}
		}

		//return atrTSLs[index];
		return atrIndicator.getValue(index);
	}

	public static void main(String args[]) {
		TimeSeries timeSeries = DataUtil.getTimeSeries(StockSymbols.RELIANCE.name, CandleStickInterval.DAY);
		ATRTrailingStopLoss indicator = new ATRTrailingStopLoss(timeSeries, 7, 3);
		int length = timeSeries.getBarCount();
		for (int i = 0; i < length; i++) {
			Bar bar = timeSeries.getBar(i);
			System.out.println(
					bar.getSimpleDateName() + "	" + bar.getClosePrice().intValue() + "	" + indicator.getValue(i).intValue());
		}
	}
}