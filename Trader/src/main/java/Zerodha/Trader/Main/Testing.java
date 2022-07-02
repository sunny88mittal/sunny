package Zerodha.Trader.Main;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.kiteconnect.utils.Constants;
import com.zerodhatech.models.Position;

import Zerodha.Trader.Core.AppConstants;
import Zerodha.Trader.Core.AuthHandler;
import Zerodha.Trader.Core.KiteHandler;

public class Testing {

	public static void main(String args[]) throws JSONException, IOException, KiteException, InterruptedException {
		AuthHandler authHandler = new AuthHandler(AppConstants.USER_ID, AppConstants.API_KEY, AppConstants.API_SECRET,
				AppConstants.REQUEST_TOKEN);
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
