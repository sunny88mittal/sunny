package Data;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import Constants.NSEHolidays;
import Constants.StockSymbols;
import Data.OptionsChainDownloader.EXPIRY;
import Entities.OptionsChain;
import Entities.OptionsChainInterpretation;

@Component
public class OptionsChainDataManager {

	private static Map<String, OptionsChainDownloader> optionsDownloaderMap = new HashMap<String, OptionsChainDownloader>();

	static {
		// Added indexes with weekly expiry
		List<String> stocksList = new ArrayList<String>();
		stocksList.add(StockSymbols.BANKNIFTY.name);
		stocksList.add(StockSymbols.NIFTY.name);
		for (String stock : stocksList) {
			OptionsChainDownloader optionsChainDownloader = new OptionsChainDownloader(stock, EXPIRY.WEEKLY);
			optionsDownloaderMap.put(stock, optionsChainDownloader);
		}

		// Add stocks with expiry as monthly
		stocksList = new ArrayList<String>();
		stocksList.add(StockSymbols.RELIANCE.name);
		stocksList.add(StockSymbols.TCS.name);
		stocksList.add(StockSymbols.INFY.name);
		stocksList.add(StockSymbols.HDFCBANK.name);
		for (String stock : stocksList) {
			OptionsChainDownloader optionsChainDownloader = new OptionsChainDownloader(stock, EXPIRY.MONTHLY);
			optionsDownloaderMap.put(stock, optionsChainDownloader);
		}
	}

	public static List<OptionsChainInterpretation> getOptionschainInterpretations(String symbol) {
		return optionsDownloaderMap.get(symbol).getOptionschainInterpretations();
	}

	public static OptionsChain getLatestOptionsChain(String symbol) throws IOException, InterruptedException {
		return optionsDownloaderMap.get(symbol).getLatestOptionsChain();
	}

	@Scheduled(cron = "0 0 9 * * MON-FRI")
	public static void updateOptionsURLs() {
		for (OptionsChainDownloader optionsChainDownloader : optionsDownloaderMap.values()) {
			optionsChainDownloader.updateOptionsURLs();
		}
	}

	@Scheduled(cron = "0 0 9 * * MON-FRI")
	public static void loadPreviousDataFromDisk() {
		LocalDateTime now = LocalDateTime.now();
		if (!NSEHolidays.isHoliday(now)) {
			for (OptionsChainDownloader optionsChainDownloader : optionsDownloaderMap.values()) {
				optionsChainDownloader.loadPreviousDataFromDisk();
			}
		}
	}

	@Scheduled(cron = "0 */3 9-15 * * MON-FRI")
	public static void updateOptionsData() throws IOException, InterruptedException {
		// Constants to keep track of time and day
		LocalDateTime nineFourteenAM = LocalDateTime.now().withHour(9).withMinute(14);
		LocalDateTime threeThirtyTwoPM = LocalDateTime.now().withHour(15).withMinute(32);
		LocalDateTime now = LocalDateTime.now();

		// Getting latest options chain
		if (now.isAfter(nineFourteenAM) && now.isBefore(threeThirtyTwoPM) && !NSEHolidays.isHoliday(now)) {
			for (OptionsChainDownloader optionsChainDownloader : optionsDownloaderMap.values()) {
				optionsChainDownloader.updateOptionsData();
			}
		}
	}

	public static void main(String args[]) throws IOException, InterruptedException {
		updateOptionsData();
	}
}
