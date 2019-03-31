package com.sunny.strategy;

import com.sunny.file.reader.impl.CSVFileReader;
import com.sunny.indicators.base.IIndicator;
import com.sunny.indicators.impl.MovingAverage;
import com.sunny.indicators.impl.MovingAverage.MovingAverageType;
import com.sunny.operators.RuleOperator;
import com.sunny.rule.base.IRule;
import com.sunny.rule.impl.RuleImpl;

public class Strategy {

	private String stockName;

	private IRule rule;

	private String startDate;

	private String endDate;

	public Strategy(String stockName, IRule rule, String startDate, String endDate) {
		super();
		this.stockName = stockName;
		this.rule = rule;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public float run() {
		String fileName = "C:\\Users\\sunmitta\\code\\sunny\\StocksAnalyst\\data\\YESBANK.csv";
		CSVFileReader fileReader = new CSVFileReader(fileName);
		String[][] data = fileReader.getRows();

		Boolean state = null;
		Float buyPrice = null;
		int profitableTrades = 0;
		int loosingTrades = 0;
		float totalProfit = 0f;
		float totalLoss = 0f;
		for (int i = 20; i < data.length; i++) {
			if (rule.evaluate(data[i][2])) {
				if (state == null || !state) {
					state = true;
					buyPrice = Float.parseFloat(data[i][8]);
					System.out.println("Buy On :" + data[i][2] + " at " +data[i][8]);
				}
			} else if (state != null && state) {
				state = false;
				float sellPrice = Float.parseFloat(data[i][8]);
				if (sellPrice > buyPrice) {
					++profitableTrades;
					totalProfit += (sellPrice - buyPrice);
				} else {
					++loosingTrades;
					totalLoss += (sellPrice - buyPrice);
				}
				 System.out.println("Sell On :" + data[i][2] + " at " +data[i][8]);
			}
		}

		System.out.println("Profitable tardes : " + profitableTrades + " profit " + totalProfit);
		System.out.println("Loosing tardes : " + loosingTrades + " loss " + totalLoss);
		System.out.println("Total Profit :" + (totalProfit + totalLoss));
		return totalProfit + totalLoss;
	}

	public static void main(String args[]) {
		String fileName = "C:\\Users\\sunmitta\\code\\sunny\\StocksAnalyst\\data\\YESBANK.csv";
		CSVFileReader fileReader = new CSVFileReader(fileName);
		String[][] data = fileReader.getRows();

		float maxProfit = -10000000f;
		String macd = "";
		for (int i = 4; i <= 8; i++) {
			for (int j = i + 2; j <= i * 2.5; j++) {
				String tmacd = "MACD " + i + "-" + j;
				System.out.println(tmacd);
				IIndicator macd4 = new MovingAverage(MovingAverageType.SIMPLE, 7, data);
				IIndicator macd10 = new MovingAverage(MovingAverageType.SIMPLE, 17, data);
				IRule rule = new RuleImpl(macd4, macd10, RuleOperator.GreaterThan);

				Strategy strategy = new Strategy("IBULHSGFIN", rule, "13-Apr-17", "29-Mar-19");
				float profit = strategy.run();
				if (profit > maxProfit) {
					maxProfit = profit;
					macd = tmacd;
				}
				System.out.println();
				break;
			}
			break;
		}
		
		System.out.println("Max profit " + maxProfit + "and is at " + macd);
		
	}
}

