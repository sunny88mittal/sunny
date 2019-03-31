package com.sunny.test.file.reader;

import org.junit.Test;

import com.sunny.file.reader.impl.CSVFileReader;

public class CSVFileReaderTests {

	String fileName = "C:\\Users\\sunmitta\\code\\sunny\\StocksAnalyst\\data\\30-03-2017-TO-29-03-2019IBULHSGFINEQN.csv";

	@Test
	public void testReader() {
		CSVFileReader reader = new CSVFileReader(fileName);
		String[] header = reader.getHeader();
		String[][] dataRows = reader.getRows();
		
		assert (header.length != 0);
		assert (dataRows.length != 0);

		/*for (String str : header) {
			System.out.println(str);
		}*/
	}
}
