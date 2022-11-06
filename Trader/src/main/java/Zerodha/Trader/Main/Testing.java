package Zerodha.Trader.Main;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.json.JSONException;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.kiteconnect.utils.Constants;
import com.zerodhatech.models.Position;

import Zerodha.Trader.Core.AuthHandler;
import Zerodha.Trader.Core.KiteHandler;
import Zerodha.Trader.Core.UserDetails;
import Zerodha.Trader.Core.UsersProvider;

public class Testing {

	public static void main(String args[])
			throws JSONException, IOException, KiteException, InterruptedException, ParseException {
		UsersProvider usersProvider = new UsersProvider();
		List<UserDetails> users = usersProvider.getAllUsers();
		UserDetails user = users.get(0);
		AuthHandler authHandler = new AuthHandler(user.USER_ID, user.API_KEY, user.API_SECRET, user.REQUEST_TOKEN);
		KiteConnect connection = authHandler.doLogin();
		KiteHandler kiteHandler = new KiteHandler(connection);
	}

	private static void testOrderPlacement(KiteHandler kiteHandler) throws JSONException, IOException, KiteException {
		kiteHandler.placeMarketOrder(25, "BANKNIFTY2191634500PE", Constants.EXCHANGE_NFO,
				Constants.TRANSACTION_TYPE_SELL);

		List<Position> positions = kiteHandler.getPositions().get("net");

		for (Position position : positions) {
			System.out.println(position.instrumentToken + " " + position.tradingSymbol + " " + position.netQuantity);
		}
	}
}
