package Statistics;

import java.util.ArrayList;
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
			statistics.onlyProft +=  profit;
		} else {
			++statistics.loosingtrades;
			statistics.onlyLoss += profit;
		}
		statistics.charges += charges;
		statistics.grossProfit += profit;
		++statistics.totalTrades;
	}
}
