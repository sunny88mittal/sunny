package Statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ta4j.core.Bar;

public class StatisticsCollector {

	public List<DataPoint> dataPoints = new ArrayList<DataPoint>();

	public Statistics statistics = new Statistics();

	public void addDataPoint(Bar startBar, Bar endBar) {
		dataPoints.add(new DataPoint(startBar, endBar));
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
			for (DataPoint dataPoint : dataPoints) {
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

			for (DataPoint dataPoint : dataPoints) {
				System.out.println();
				dataPoint.print();
			}
		}
	}
}
