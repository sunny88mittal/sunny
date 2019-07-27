package Statistics;

import org.ta4j.core.Bar;

public class DataPoint {

	public Bar startBar;
	
	public Bar endBar;
	
	public DataPoint(Bar startBar, Bar endBar) {
		this.startBar = startBar;
		this.endBar = endBar;
	}
}
