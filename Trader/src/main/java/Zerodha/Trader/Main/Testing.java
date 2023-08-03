package Zerodha.Trader.Main;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.json.JSONException;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Order;

import Zerodha.Trader.Core.UserDetails;
import Zerodha.Trader.Services.AuthHandler;
import Zerodha.Trader.Services.KiteHandler;
import Zerodha.Trader.Services.UsersProvider;

public class Testing {

	public static void main(String args[])
			throws JSONException, IOException, KiteException, InterruptedException, ParseException {
		UsersProvider usersProvider = new UsersProvider();
		List<UserDetails> users = usersProvider.getAllUsers();
		UserDetails user = users.get(0);
		AuthHandler authHandler = new AuthHandler(user.USER_ID, user.API_KEY, user.API_SECRET, user.REQUEST_TOKEN);
		KiteConnect connection = authHandler.doLogin();
		KiteHandler kiteHandler = new KiteHandler(connection);
		testOrderPlacement(kiteHandler);
	}

	private static void testOrderPlacement(KiteHandler kiteHandler) throws JSONException, IOException, KiteException {
		Order order = kiteHandler.placeMarketOrder(25, "BANKNIFTY2380346000CE", "NFO", "SELL");
		System.out.println(order);
	}
}
