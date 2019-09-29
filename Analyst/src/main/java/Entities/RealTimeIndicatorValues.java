package Entities;

import java.util.Map;

public class RealTimeIndicatorValues {

	public String interval;

	public Map<String, String> indicators;

	public RealTimeIndicatorValues(String interval, Map<String, String> indicators) {
		super();
		this.interval = interval;
		this.indicators = indicators;
	}
}
