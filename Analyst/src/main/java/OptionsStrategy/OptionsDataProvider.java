package OptionsStrategy;

import java.io.File;

import Constants.FileConstants;
import Constants.StockSymbols;
import Entities.OptionsChain;
import Helper.IOHelper;
import OptionsData.NewOptionsChainBuilder;

public class OptionsDataProvider {

	public static OptionsChain getData(String instrument, String date, int index) {
		String dirLocation = FileConstants.OPTIONS_FILE_BASE_PATH + "\\" + date + "\\" + instrument;
		File[] files = IOHelper.getFilesInDir(dirLocation);

		if (index >= files.length) {
			return null;
		}

		File file = files[index];
		String fileContents = IOHelper.readFile(file.getAbsolutePath());
		long lastModifiedTime = file.lastModified();
		OptionsChain optionsChain = NewOptionsChainBuilder.getOptionsChain(fileContents, lastModifiedTime);

		return optionsChain;
	}
	
	public static void main (String args[]) {
		OptionsChain optionsChain = getData(StockSymbols.BANKNIFTY.name, "11-03-2022", 0);
		System.out.println(optionsChain.symbol);
	}
}
