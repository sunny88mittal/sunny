package Trading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ta4j.core.Bar;

public class TradesCollector {

	public List<Trade> dataPoints = new ArrayList<Trade>();

	public Map<String, String> statsMeta = new LinkedHashMap<String, String>();

	public TradesSummary statistics = new TradesSummary();

	public void addDataPoint(Bar startBar, Bar endBar) {
		dataPoints.add(new Trade(startBar, endBar));
		float startAt = startBar.getClosePrice().floatValue();
		float endAt = endBar.getClosePrice().floatValue();
		float profit = endAt - startAt;
		float charges = ((startAt + endAt) / 10000) * 3;
		if (profit >= 0.0) {
			++statistics.profitableTrades;
			statistics.onlyProft += profit;
		} else {
			++statistics.loosingtrades;
			statistics.onlyLoss += profit;
		}
		statistics.charges += charges;
		statistics.grossProfit += profit;
		++statistics.totalTrades;
	}

	public void printStats(boolean printDetails) {
		statistics.printStats();

		if (printDetails) {
			List<Float> profits = new ArrayList<Float>();
			List<Float> losses = new ArrayList<Float>();
			for (Trade dataPoint : dataPoints) {
				float startAt = dataPoint.startBar.getClosePrice().floatValue();
				float endAt = dataPoint.endBar.getClosePrice().floatValue();
				float profit = endAt - startAt;
				if (profit > 0) {
					profits.add(profit);
				} else {
					losses.add(profit);
				}
			}

			Collections.sort(profits);
			Collections.sort(losses);
			System.out.println();
			System.out.println(profits);
			System.out.println();
			System.out.println(losses);

			for (Trade dataPoint : dataPoints) {
				System.out.println();
				dataPoint.print();
			}
		}
	}
}
