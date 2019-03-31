package com.sunny.file.reader.impl;

import com.sunny.file.reader.base.BaseFileReader;
import com.sunny.file.reader.base.IFileReader;

public class CSVFileReader extends BaseFileReader implements IFileReader {

	public CSVFileReader(String fileLocation) {
		super(fileLocation);
	}

	@Override
	public String[] getHeader() {
		String header = getHeadRow();
		return header.split(",");
	}

	@Override
	public String[][] getRows() {
		String[] dataRows = getDataRows();
		int columns = dataRows[0].split(",").length;
		String[][] data = new String[dataRows.length][columns];
		for (int i = 0; i < dataRows.length; i++) {
			data[i] = dataRows[i].split(",");
		}
		return data;
	}
}
