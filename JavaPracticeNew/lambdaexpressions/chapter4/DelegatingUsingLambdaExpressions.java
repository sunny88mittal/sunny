package chapter4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.function.Function;

public class DelegatingUsingLambdaExpressions {

	// Web Service to get stock price
	public static class YahooFinance {
		public static BigDecimal getPrice(final String ticker) {
			try {
				final URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker);
				final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
				final String data = reader.lines().skip(1).findFirst().get();
				final String[] dataItems = data.split(",");
				return new BigDecimal(dataItems[dataItems.length - 1]);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	public static class CalculateNAV {
		private Function<String, BigDecimal> priceFinder;

		public CalculateNAV(Function<String, BigDecimal> priceFinder) {
			this.priceFinder = priceFinder;
		}

		public BigDecimal computeStockWorth(final String ticker, final int shares) {
			return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
		}
	}

	public static void main(String args[]) {
		final CalculateNAV calculateNav = new CalculateNAV(YahooFinance::getPrice);
		System.out.println(
				String.format("100 shares of Google worth: $%.2f", calculateNav.computeStockWorth("GOOG", 100)));
	}
}