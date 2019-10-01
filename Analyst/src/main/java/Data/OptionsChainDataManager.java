package Data;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;

import Constants.NSEHolidays;
import Constants.StockSymbols;
import Entities.OptionsChain;
import Entities.OptionsChainInterpretation;

public class OptionsChainDataManager {

	private static Map<String, OptionsChainDownloader> optionsDownloaderMap = new HashMap<String, OptionsChainDownloader>();

	static {
		String bankNifty = StockSymbols.BANKNIFTY.name;
		String nifty = StockSymbols.NIFTY.name;
		OptionsChainDownloader bankNiftyDownloader = new OptionsChainDownloader(bankNifty);
		OptionsChainDownloader niftyDownloader = new OptionsChainDownloader(nifty);
		optionsDownloaderMap.put(bankNifty, bankNiftyDownloader);
		optionsDownloaderMap.put(nifty, niftyDownloader);
	}

	public static List<OptionsChainInterpretation> getOptionschainInterpretations(String symbol) {
		return optionsDownloaderMap.get(symbol).getOptionschainInterpretations();
	}

	public static OptionsChain getLatestOptionsChain(String symbol) throws IOException, InterruptedException {
		return optionsDownloaderMap.get(symbol).getLatestOptionsChain();
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
}
