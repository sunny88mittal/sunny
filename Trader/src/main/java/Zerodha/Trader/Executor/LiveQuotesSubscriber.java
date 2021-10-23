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

	private final int TICKER_RECONNECT_TIME = 15 * 1000;

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
				while (!tickerProvider.isConnectionOpen()) {
					try {
						Thread.sleep(TICKER_RECONNECT_TIME);
						connectTicker(tickerProvider);
					} catch (KiteException ke) {
						Logger.print(this.getClass(), "Error connecting ticker");
					} catch (InterruptedException e) {
						Logger.print(this.getClass(), "Error connecting ticker");
					}
				}
			}
		});

		/** Set error listener to listen to errors. */
		tickerProvider.setOnErrorListener(new OnError() {
			public void onError(Exception exception) {
				Logger.print(this.getClass(), "Error in ticker connection");
			}

			public void onError(KiteException kiteException) {
				Logger.print(this.getClass(), "Error in ticker connection");
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
		
		connectTicker(tickerProvider);
	}

	private void connectTicker(KiteTicker tickerProvider) throws KiteException {
		// Make sure this is called before calling connect.
		tickerProvider.setTryReconnection(true);

		// maximum retries and should be greater than 0
		tickerProvider.setMaximumRetries(100);

		// set maximum retry interval in seconds
		tickerProvider.setMaximumRetryInterval(30);

		tickerProvider.connect();

		Logger.print(this.getClass(), "Tikcer Provider Connection Open : " + tickerProvider.isConnectionOpen());
	}
}
