package Indicators;

import java.util.List;

import Entities.CandleStickData;

public class MovingAverage {

	private MovingAverageType type;

	private int noOfPoints;

	public static enum MovingAverageType {
		SIMPLE, EXPONENTIAL
	}

	public MovingAverage(MovingAverageType type, int noOfPoints) {
		this.type = type;
		this.noOfPoints = noOfPoints;
	}

	public MovingAverageType getType() {
		return type;
	}

	public int getNoOfPoints() {
		return noOfPoints;
	}

	public float getValue(String date, List<CandleStickData> candleSticksData) {
		float data[] = null;
		float movingAvergae = 0;
		switch (type) {
		case SIMPLE:
			int length = data.length;
			float sum = 0;
			for (float point : data) {
				sum += point;
			}
			movingAvergae = sum / length;
			break;
		case EXPONENTIAL:
			break;
		default:
			break;
		}
		return movingAvergae;
	}
}
