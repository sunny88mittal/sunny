package Zerodha.Trader.Executor;

import java.util.ArrayList;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Tick;
import com.zerodhatech.ticker.KiteTicker;
import com.zerodhatech.ticker.OnConnect;
import com.zerodhatech.ticker.OnDisconnect;
import com.zerodhatech.ticker.OnError;
import com.zerodhatech.ticker.OnTicks;

import Zerodha.Trader.Logging.Logger;
import Zerodha.Trader.Strategy.IStrategy;

public class LiveQuotesSubscriber {

	private ArrayList<Long> tokens;

	private final KiteTicker tickerProvider;

	private final IStrategy strategy;

	public LiveQuotesSubscriber(KiteConnect kiteConnect, ArrayList<Long> tokens, IStrategy strategy) {
		super();
		this.tokens = tokens;
		this.strategy = strategy;
		tickerProvider = new KiteTicker(kiteConnect.getAccessToken(), kiteConnect.getApiKey());
	}

	public void connect() throws KiteException {
		tickerProvider.setOnConnectedListener(new OnConnect() {
			public void onConnected() {
				tickerProvider.subscribe(tokens);
				tickerProvider.setMode(tokens, KiteTicker.modeLTP);
				Logger.print(this.getClass(), "Ticker Connected");
			}
		});

		tickerProvider.setOnDisconnectedListener(new OnDisconnect() {
			public void onDisconnected() {
				Logger.print(this.getClass(), "Ticker disconnected");
			}
		});

		/** Set error listener to listen to errors. */
		tickerProvider.setOnErrorListener(new OnError() {
			public void onError(Exception exception) {
				Logger.print(this.getClass(), exception.getMessage());
			}

			public void onError(KiteException kiteException) {
				Logger.print(this.getClass(), kiteException.getMessage());
			}

			public void onError(String error) {
				Logger.print(this.getClass(), error);
			}
		});

		tickerProvider.setOnTickerArrivalListener(new OnTicks() {
			public void onTicks(ArrayList<Tick> ticks) {
				if (ticks.size() > 0) {
					strategy.doNext(ticks.get(0).getLastTradedPrice());
				}
			}
		});

		// Make sure this is called before calling connect.
		tickerProvider.setTryReconnection(true);

		// maximum retries and should be greater than 0
		tickerProvider.setMaximumRetries(10);

		// set maximum retry interval in seconds
		tickerProvider.setMaximumRetryInterval(30);

		tickerProvider.connect();

		Logger.print(this.getClass(), "Tikcer Provider Connection Open : " + tickerProvider.isConnectionOpen());
	}
}
