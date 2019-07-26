package Indicators;

import java.util.List;

import Entities.CandleStickData;

public class Momentum {

	private int noOfPoints;

	public Momentum(int noOfPoints) {
		this.noOfPoints = noOfPoints;
	}

	public float getValue(String timestamp, List<CandleStickData> candleSticksData) {
		return 1;
	}
}
