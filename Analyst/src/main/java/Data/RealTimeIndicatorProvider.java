package Data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.ParabolicSarIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import Constants.CandleStickInterval;
import Constants.StockSymbols;
import Constants.TradeConstants;
import DataUtil.DataUtil;
import Indicators.MACDWithSignalIndicator;
import Indicators.SuperTrendIndicator;

public class RealTimeIndicatorProvider {

	private static final String PSAR = "PSAR";

	private static final String MACD = "MACD";

	private static final String SUPERTREND = "SUPERTREND";

	public static Map<String, Map<String, String>> getIndicatorsFor() throws IOException {
		Map<String, Map<String, String>> signalsValues = new HashMap<String, Map<String, String>>();
		signalsValues.put(CandleStickInterval.MINUTE_3,
				getIndicatorsSignals(StockSymbols.BANKNIFTY, CandleStickInterval.MINUTE_3));
		signalsValues.put(CandleStickInterval.MINUTE_5,
				getIndicatorsSignals(StockSymbols.BANKNIFTY, CandleStickInterval.MINUTE_5));
		signalsValues.put(CandleStickInterval.MINUTE_15,
				getIndicatorsSignals(StockSymbols.BANKNIFTY, CandleStickInterval.MINUTE_15));
		return signalsValues;
	}

	private static Map<String, String> getIndicatorsSignals(StockSymbols stockSymbol, String candleStickInterval)
			throws IOException {
		Map<String, String> signalsValues = new HashMap<String, String>();

		String data = StocksDataDownloader.getRealTimeData(stockSymbol, candleStickInterval);
		TimeSeries series = DataUtil.convertToTimeseries(DataUtil.getCandeStickData(data));
		ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);

		MACDWithSignalIndicator macd = new MACDWithSignalIndicator(closePriceIndicator);
		ParabolicSarIndicator psar = new ParabolicSarIndicator(series);
		SuperTrendIndicator superTrend = new SuperTrendIndicator(series, 7, 3);

		int index = series.getEndIndex();
		Bar bar = series.getBar(index);
		double closePrice = bar.getClosePrice().doubleValue();

		double psarValue = psar.getValue(index).doubleValue();
		double macdValue = macd.getValue(index).doubleValue();
		double superTrendValue = superTrend.getValue(index).doubleValue();

		if (psarValue > closePrice) {
			signalsValues.put(PSAR, TradeConstants.SELL);
		} else {
			signalsValues.put(PSAR, TradeConstants.BUY);
		}

		if (macdValue > 0) {
			signalsValues.put(MACD, TradeConstants.BUY);
		} else {
			signalsValues.put(MACD, TradeConstants.SELL);
		}

		if (superTrendValue > closePrice) {
			signalsValues.put(SUPERTREND, TradeConstants.SELL);
		} else {
			signalsValues.put(SUPERTREND, TradeConstants.BUY);
		}

		return signalsValues;
	}
}