package Zerodha.Trader.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.User;

import Zerodha.Trader.Core.AppConstants;
import Zerodha.Trader.Core.AuthDetails;

public class AuthHandler {

	private String userId;

	private String apiKey;

	private String apiSecret;

	private String requestToken;

	private static String USER_ID = "USER_ID";

	public AuthHandler(String userId, String apiKey, String apiSecret, String requestToken) {
		this.userId = userId;
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		this.requestToken = requestToken;
	}

	public KiteConnect doLogin() throws JSONException, IOException, KiteException {
		String authFileName = AppConstants.AUTH_DETAILS_FILE_PATH.replace(USER_ID, this.userId);
		File file = new File(authFileName);
		if (file.exists()) {
			return createConnectionFromFile(file);
		}
		return getConnectionUsingRequestToken(authFileName);
	}

	private KiteConnect getConnectionUsingRequestToken(String fileName)
			throws JSONException, IOException, KiteException {
		// Initialize Kiteconnect using apiKey.
		KiteConnect connection = new KiteConnect(apiKey);

		// Set userId.
		connection.setUserId(userId);

		// Get accessToken as follows,
		User user = connection.generateSession(requestToken, apiSecret);

		// Set request token and public token which are obtained from login process.
		connection.setAccessToken(user.accessToken);
		connection.setPublicToken(user.publicToken);

		// Write auth details to file
		Gson gson = new Gson();
		AuthDetails authDetails = new AuthDetails(userId, user.accessToken, user.publicToken);
		FileWriter fileWriter = new FileWriter(fileName);
		fileWriter.write(gson.toJson(authDetails));
		fileWriter.flush();
		fileWriter.close();

		return connection;
	}

	private KiteConnect createConnectionFromFile(File file)
			throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		AuthDetails authDetails = gson.fromJson(new FileReader(file), AuthDetails.class);

		KiteConnect connection = new KiteConnect(apiKey);
		connection.setUserId(userId);
		connection.setAccessToken(authDetails.accessToken);
		connection.setPublicToken(authDetails.publicToken);

		return connection;
	}
}
