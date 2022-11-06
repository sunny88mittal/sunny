package Zerodha.Trader.Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;

import Zerodha.Trader.Core.AppConstants;
import Zerodha.Trader.Core.AuthHandler;
import Zerodha.Trader.Core.KiteHandler;
import Zerodha.Trader.Core.KiteUser;
import Zerodha.Trader.Core.UserDetails;
import Zerodha.Trader.Core.UsersProvider;
import Zerodha.Trader.Executor.LiveQuotesSubscriber;
import Zerodha.Trader.Strategy.IStrategy;
import Zerodha.Trader.Strategy.ShortStraddleWithAdjustment;

public class MainApp {

	private static KiteConnect quotesConnection = null;

	public static void main(String args[])
			throws JSONException, IOException, KiteException, InterruptedException, ParseException {

		// Create connections for all users
		List<KiteUser> kiteUsers = getKiteUsers();

		// Create strategy instance
		IStrategy strategy = new ShortStraddleWithAdjustment(AppConstants.OPTION_DATE_VALUE, kiteUsers);
		strategy.initialize();

		// Start listening to the quotes and trade
		ArrayList<Long> quotes = new ArrayList<Long>();
		quotes.add(AppConstants.BANKNIFTY_QUOTE);
		LiveQuotesSubscriber quotesSubscriber = new LiveQuotesSubscriber(quotesConnection, quotes, strategy);
		quotesSubscriber.connect();
	}

	private static List<KiteUser> getKiteUsers()
			throws FileNotFoundException, IOException, ParseException, JSONException, KiteException {
		List<KiteUser> kiteUsers = new ArrayList<KiteUser>();

		for (UserDetails userDetails : getAllUsers()) {
			AuthHandler authHandler = new AuthHandler(userDetails.USER_ID, userDetails.API_KEY, userDetails.API_SECRET,
					userDetails.REQUEST_TOKEN);
			KiteConnect connection = authHandler.doLogin();
			if (quotesConnection == null) {
				quotesConnection = connection;
			}
			KiteHandler kiteHandler = new KiteHandler(connection);
			KiteUser kiteUser = new KiteUser(userDetails.QTY, userDetails.NAME, kiteHandler);
			kiteUsers.add(kiteUser);
		}
		return kiteUsers;
	}

	private static List<UserDetails> getAllUsers() throws FileNotFoundException, IOException, ParseException {
		UsersProvider usersProvider = new UsersProvider();
		List<UserDetails> users = usersProvider.getAllUsers();
		return users;
	}
}
