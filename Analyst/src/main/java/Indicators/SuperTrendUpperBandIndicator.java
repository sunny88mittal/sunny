package Indicators;

import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.ATRIndicator;
import org.ta4j.core.indicators.RecursiveCachedIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.PrecisionNum;

public class SuperTrendUpperBandIndicator extends RecursiveCachedIndicator<Num> {

	private static final long serialVersionUID = -4633609484867965441L;

	private int multiplier;

	private ATRIndicator atrIndicator;

	public SuperTrendUpperBandIndicator(TimeSeries series, int period, int multiplier) {
		super(series);
		this.multiplier = multiplier;
		atrIndicator = new ATRIndicator(series, period);
	}

	@Override
	protected Num calculate(int index) {
		Num twoAsNum = PrecisionNum.valueOf(2);
		Num muliplier = PrecisionNum.valueOf(multiplier);
		Bar currentBar = this.getTimeSeries().getBar(index);

		Num low = currentBar.getMinPrice();
		Num high = currentBar.getMaxPrice();
		Num atrValue = atrIndicator.getValue(index);

		Num basicUpperBand = low.plus(high).dividedBy(twoAsNum).plus(muliplier.multipliedBy(atrValue));
		if (index <= 0) {
			return basicUpperBand;
		}

		Num previousFinalUpperBand = getValue(index - 1);
		Num previousClose = this.getTimeSeries().getBar(index - 1).getClosePrice();

		Num finalUpperBand = previousFinalUpperBand;
		if (basicUpperBand.isLessThan(previousFinalUpperBand) || previousClose.isGreaterThan(previousFinalUpperBand)) {
			finalUpperBand = basicUpperBand;
		}
		
		//System.out.println(currentBar.getSimpleDateName() + " " + finalUpperBand);
		return finalUpperBand;
	}
}
