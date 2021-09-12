package Zerodha.Trader.Core;

import java.io.IOException;

import org.json.JSONException;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.User;

public class AuthHandler {

	private String userId;

	private String apiKey;

	private String apiSecret;
	
	private String requestToken;

	public AuthHandler(String userId, String apiKey, String apiSecret, String requestToken) {
		this.userId = userId;
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		this.requestToken = requestToken;
	}

	public KiteConnect doLogin() throws JSONException, IOException, KiteException {
		// Initialize Kiteconnect using apiKey.
		KiteConnect kiteSdk = new KiteConnect(apiKey);

		// Set userId.
		kiteSdk.setUserId(userId);

		// Get accessToken as follows,
		User user = kiteSdk.generateSession(requestToken, apiSecret);

		// Set request token and public token which are obtained from login process.
		kiteSdk.setAccessToken(user.accessToken);
		kiteSdk.setPublicToken(user.publicToken);

		return kiteSdk;
	}
}
