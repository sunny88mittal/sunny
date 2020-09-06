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

	private Num multiplier;

	private TimeSeries series;

	private ATRIndicator atrIndicator;

	public ATRTrailingStopLoss(TimeSeries series, int period, int multiplier) {
		super(series);
		this.multiplier = PrecisionNum.valueOf(multiplier);
		this.series = series;
		atrIndicator = new ATRIndicator(series, period);
	}

	@Override
	protected Num calculate(int index) {
		Num invertedAt = series.getBar(0).getClosePrice();
		Num atrTSL = invertedAt.minus(atrIndicator.getValue(0).multipliedBy(multiplier));

		for (int i = 1; i <= index; i++) {
			Num curClose = series.getBar(i).getClosePrice();
			Num prevClose = series.getBar(i - 1).getClosePrice();
			Num atrWithMultiplier = atrIndicator.getValue(i).multipliedBy(multiplier);
			if (curClose.isLessThan(prevClose)) {
				if (atrTSL.isGreaterThan(curClose)) { // Down trend starts or continues
					if (curClose.isLessThan(invertedAt)) {
						invertedAt = curClose;
						atrTSL = curClose.plus(atrWithMultiplier);
					}
				}
			} else {
				if (atrTSL.isLessThan(curClose)) {
					if (curClose.isGreaterThan(invertedAt)) { // Up trend starts or continues
						invertedAt = curClose;
						atrTSL = curClose.minus(atrWithMultiplier);
					}
				}
			}
		}

		return atrTSL;
	}

	public static void main(String args[]) {
		TimeSeries timeSeries = DataUtil.getTimeSeries(StockSymbols.RELIANCE.name, CandleStickInterval.DAY);
		int length = timeSeries.getBarCount();
		ATRTrailingStopLoss atrTrailingStopLoss = new ATRTrailingStopLoss(timeSeries, 7, 3);
		for (int i = 0; i < length; i++) {
			Bar bar = timeSeries.getBar(i);
			System.out.println(
					bar.getSimpleDateName() + "," + bar.getClosePrice().intValue() + "," + bar.getMaxPrice().intValue()
							+ "," + bar.getMinPrice().intValue() + "," + atrTrailingStopLoss.getValue(i).intValue());
		}
	}
}