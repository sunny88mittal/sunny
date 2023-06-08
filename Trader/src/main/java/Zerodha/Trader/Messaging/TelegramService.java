package Zerodha.Trader.Messaging;

import java.io.IOException;

public class TelegramService {

	private static String MESSAGE_HTTP_URL = "https://api.telegram.org/bot<token>/sendMessage?chat_id=-677540835&text=MESSAGE";
	
	public static void sendMessage(String message) {
		String finalURL = MESSAGE_HTTP_URL.replace("MESSAGE", message);
		try {
			NetworkHelper.makeGetRequest(finalURL);
		} catch (IOException e) {
			//Do nothing
		}
	}
	
	public static void main (String args[]) throws IOException {
		sendMessage("Testing");
	}
}
