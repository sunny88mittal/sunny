package com.sunny.indicators.impl;

import com.sunny.indicators.base.IIndicator;

public class MovingAverage implements IIndicator {

	private MovingAverageType type;

	private int noOfPoints;
	
	private String[][] data;

	public static enum MovingAverageType {
		SIMPLE, EXPONENTIAL
	}

	public MovingAverage(MovingAverageType type, int noOfPoints, String [][] data) {
		this.type = type;
		this.noOfPoints = noOfPoints;
		this.data = data;
	}

	public MovingAverageType getType() {
		return type;
	}

	public int getNoOfPoints() {
		return noOfPoints;
	}

	public float getValue(String date) {
		float data[] = new float[]{};
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
