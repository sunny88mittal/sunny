package Zerodha.Trader.Main;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;

import Zerodha.Trader.Core.AppConstants;
import Zerodha.Trader.Core.AuthHandler;
import Zerodha.Trader.Core.OrderHandler;
import Zerodha.Trader.Core.PositionsProvider;
import Zerodha.Trader.Executor.LiveQuotesSubscriber;
import Zerodha.Trader.Strategy.IStrategy;
import Zerodha.Trader.Strategy.ShortStraddleWithAdjustment;

public class MainApp {

	public static void main(String args[]) throws JSONException, IOException, KiteException, InterruptedException {
		AuthHandler authHandler = new AuthHandler(AppConstants.USER_ID, AppConstants.API_KEY, AppConstants.API_SECRET,
				AppConstants.REQUEST_TOKEN);
		KiteConnect connection = authHandler.doLogin();

		OrderHandler orderHandler = new OrderHandler(connection);

		PositionsProvider positionsProvider = new PositionsProvider(connection);

		IStrategy strategy = new ShortStraddleWithAdjustment(AppConstants.QTY, AppConstants.OPTION_DATE_VALUE,
				orderHandler, positionsProvider);
		strategy.initialize();

		ArrayList<Long> quotes = new ArrayList<Long>();
		quotes.add(AppConstants.BANKNIFTY_QUOTE);
		LiveQuotesSubscriber quotesSubscriber = new LiveQuotesSubscriber(connection, quotes, strategy);

		quotesSubscriber.connect();
	}
}
