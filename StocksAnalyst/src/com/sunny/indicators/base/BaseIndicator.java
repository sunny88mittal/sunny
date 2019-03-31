package com.sunny.indicators.base;

import com.sunny.file.reader.base.ColumnConstants;

public abstract class BaseIndicator {

	private String[][] data;

	public BaseIndicator(String[][] data) {
		super();
		this.data = data;
	}

	protected float[] getClosingPriceForLastXDays(String endDate, int noOfDays) {
		float[] dataPoints = new float[noOfDays];
		int dateIndex = getColumnIndex(ColumnConstants.COLUMN_DATE);
		int closePriceIndex = getColumnIndex(ColumnConstants.COLUMN_CLOSE_PRICE);
		int rowIndex = getRowIndex(endDate, dateIndex);
		for (int i = 0; i < noOfDays; i++) {
			dataPoints[i] = Float.parseFloat(data[rowIndex - i][closePriceIndex]);
		}
		return dataPoints;
	}

	private int getColumnIndex(String name) {
		String[] header = data[0];
		int columnIndex = 0;
		for (String str : header) {
			if (str.equals(name)) {
				break;
			}
			++columnIndex;
		}
		return columnIndex;
	}

	private int getRowIndex(String value, int colIndex) {
		int rowIndex = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i][colIndex].equals(value)) {
				rowIndex = i;
				break;
			}
		}
		return rowIndex;
	}
}
