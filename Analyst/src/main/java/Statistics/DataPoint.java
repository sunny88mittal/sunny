package Statistics;

import org.ta4j.core.Bar;

public class DataPoint {

	public Bar startBar;
	
	public Bar endBar;
	
	public DataPoint(Bar startBar, Bar endBar) {
		this.startBar = startBar;
		this.endBar = endBar;
	}
	
	public void print() {
		System.out.println("BUY:" + startBar.getSimpleDateName() + " " + startBar.getClosePrice());
		System.out.println("SELL:" + endBar.getSimpleDateName() + " " + endBar.getClosePrice());
		float startAt = startBar.getClosePrice().floatValue();
		float endAt = endBar.getClosePrice().floatValue();
		float profit = endAt - startAt;
		System.out.println("PROFIT: " + profit);
	}
}
