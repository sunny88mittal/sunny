package OptionsStrategy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import Constants.FileConstants;
import Constants.StockSymbols;
import Entities.OptionsChain;
import Helper.IOHelper;
import OptionsData.NewOptionsChainBuilder;

public class OptionsDataProvider {

	public static Map<String, OptionsChain> cache = new HashMap<String, OptionsChain>();

	public static OptionsChain getData(String instrument, String date, int index) {
		String cacheKey = instrument + "-" + date + "-" + index;
		if (cache.get(cacheKey) == null) {
			String dirLocation = FileConstants.OPTIONS_FILE_BASE_PATH + "\\" + date + "\\" + instrument;
			File[] files = IOHelper.getFilesInDir(dirLocation);

			if (index >= files.length) {
				return null;
			}

			File file = files[index];
			String fileContents = IOHelper.readFile(file.getAbsolutePath());
			long lastModifiedTime = file.lastModified();
			OptionsChain optionsChain = NewOptionsChainBuilder.getOptionsChain(fileContents, lastModifiedTime);
			cache.put(cacheKey, optionsChain);
		}

		return cache.get(cacheKey);
	}

	public static void loadCache(String instrument, String date) {
		cache.clear();
		String dirLocation = FileConstants.OPTIONS_FILE_BASE_PATH + "\\" + date + "\\" + instrument;
		File[] files = IOHelper.getFilesInDir(dirLocation);
		for (int i = 0; i < files.length; i++) {
			String cacheKey = instrument + "-" + date + "-" + i;
			File file = files[i];
			String fileContents = IOHelper.readFile(file.getAbsolutePath());
			long lastModifiedTime = file.lastModified();
			OptionsChain optionsChain = NewOptionsChainBuilder.getOptionsChain(fileContents, lastModifiedTime);
			cache.put(cacheKey, optionsChain);
		}
	}

	public static void main(String args[]) {
		OptionsChain optionsChain = getData(StockSymbols.BANKNIFTY.name, "11-03-2022", 0);
		System.out.println(optionsChain.symbol);
	}
}
