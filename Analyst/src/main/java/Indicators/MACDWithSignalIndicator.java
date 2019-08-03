package Indicators;

import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.num.Num;

public class MACDWithSignalIndicator extends CachedIndicator<Num> {

	private static final long serialVersionUID = 3319328601246281474L;

	private MACDIndicator macd;

	private EMAIndicator emaMacd;

	public MACDWithSignalIndicator(Indicator<Num> indicator) {
		this(indicator, 12, 26, 9);
	}

	public MACDWithSignalIndicator(Indicator<Num> indicator, int shortEma, int longEma, int signalLine) {
		super(indicator);
		macd = new MACDIndicator(indicator, shortEma, longEma);
		emaMacd = new EMAIndicator(macd, 9);
	}

	@Override
	protected Num calculate(int index) {
		return macd.getValue(index).minus(emaMacd.getValue(index));
	}
}
