package com.sunny.file.reader.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaseFileReader {

	private String fileLocation;

	public BaseFileReader(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	protected String getHeadRow() {
		return getLines(1, 1)[0];
	}

	protected String[] getDataRows() {
		return getLines(1, Integer.MAX_VALUE);
	}

	private String[] getLines(int start, int end) {
		List<String> lines = new ArrayList<String>();
		try {
			int counter = 1;
			Scanner scanner = new Scanner(new File(fileLocation));
			while (scanner.hasNextLine() && start <= end) {
				if (counter >= start) {
					lines.add(scanner.nextLine());
				}
				++counter;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return lines.toArray(new String[0]);
	}
}
