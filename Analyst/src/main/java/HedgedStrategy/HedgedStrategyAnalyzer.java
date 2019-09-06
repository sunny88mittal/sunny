package HedgedStrategy;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;

import Constants.CandleStickInterval;
import Constants.StockSymbols;
import DataUtil.DataUtil;

public class HedgedStrategyAnalyzer {

	private static float getReturnForMonth(StockSymbols stock, int month, int year) {
		float monthlyReturn = 0.0f;
		TimeSeries series = DataUtil.getTimeSeries(stock.name, CandleStickInterval.DAY);
		Bar firstBar = null;
		Bar lastBar = null;
		for (int i = 0; i < series.getBarCount(); i++) {
			Bar bar = series.getBar(i);
			ZonedDateTime time = bar.getBeginTime();
			if (time.getMonthValue() == month && time.getYear() == year) {
				if (firstBar == null) {
					firstBar = bar;
				}
				lastBar = bar;
			}
		}

		float openPrice = firstBar.getOpenPrice().floatValue();
		float closePrice = lastBar.getClosePrice().floatValue();

		monthlyReturn = ((closePrice - openPrice) / openPrice) * 100;

		return monthlyReturn;
	}

	private static float getReturnForList(List<StockSymbols> buy, int month, int year) {
		float totalReturn = 0.0f;
		for (StockSymbols stock : buy) {
			totalReturn += getReturnForMonth(stock, month, year);
		}
		return totalReturn;
	}

	private static void testStartegy(List<StockSymbols> buy, List<StockSymbols> sell) {
		LocalDate now = LocalDate.now();
		for (int i = 0; i < 60; i++) {
			LocalDate date = now.minusMonths(i);
			float buyReturn = getReturnForList(buy, date.getMonthValue(), date.getYear());
			float sellReturn = getReturnForList(sell, date.getMonthValue(), date.getYear());
			float netReturn = (buyReturn / buy.size() - sellReturn / sell.size()) * 5;
			System.out.println("Return for : " + date.getMonth().toString().subSequence(0, 3) + "-" + date.getYear()
					+ " is " + netReturn);
		}
	}

	public static void main(String args[]) {
		List<StockSymbols> buy = new ArrayList<StockSymbols>();
		buy.add(StockSymbols.RELIANCE);
		buy.add(StockSymbols.HDFC);
		buy.add(StockSymbols.HDFCBANK);
		buy.add(StockSymbols.ICICIBANK);
		buy.add(StockSymbols.KOTAKBANK);
		buy.add(StockSymbols.INFY);
		buy.add(StockSymbols.TCS);
		buy.add(StockSymbols.HINDUNILVR);
		buy.add(StockSymbols.ITC);
		buy.add(StockSymbols.LT);

		List<StockSymbols> sell = new ArrayList<StockSymbols>();
		sell.add(StockSymbols.NIFTY);
		sell.add(StockSymbols.NIFTY);
		sell.add(StockSymbols.NIFTY);
		sell.add(StockSymbols.NIFTY);
		sell.add(StockSymbols.NIFTY);
		sell.add(StockSymbols.NIFTY);
		sell.add(StockSymbols.NIFTY);
		sell.add(StockSymbols.NIFTY);

		testStartegy(buy, sell);
	}
}
