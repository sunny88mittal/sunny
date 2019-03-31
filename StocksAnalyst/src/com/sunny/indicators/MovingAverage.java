package com.sunny.indicators;

public class MovingAverage {

	private MovingAverageType type;

	public static enum MovingAverageType {
		SIMPLE, EXPONENTIAL
	}

	public MovingAverage(MovingAverageType type) {
		this.type = type;
	}

	public float getValue(Float[] data) {
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
