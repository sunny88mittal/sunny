package Zerodha.Trader.Core;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONException;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.kiteconnect.utils.Constants;
import com.zerodhatech.models.Instrument;
import com.zerodhatech.models.Order;
import com.zerodhatech.models.OrderParams;
import com.zerodhatech.models.Position;

public class KiteHandler {

	private KiteConnect kiteConnect;

	public KiteHandler(KiteConnect kiteConnect) {
		this.kiteConnect = kiteConnect;
	}

	public Order placeMarketOrder(int qty, String tradingSymbol, String exchange, String orderType)
			throws JSONException, IOException, KiteException {
		OrderParams orderParams = new OrderParams();
		orderParams.orderType = Constants.ORDER_TYPE_MARKET;
		return placeOrder(qty, tradingSymbol, exchange, orderType, orderParams);
	}

	public Order placeLimitOrder(int qty, String tradingSymbol, String exchange, String orderType, int price)
			throws JSONException, IOException, KiteException {
		OrderParams orderParams = new OrderParams();
		orderParams.orderType = Constants.ORDER_TYPE_SL;
		orderParams.price = (double) price;
		orderParams.triggerPrice = (double) price;
		return placeOrder(qty, tradingSymbol, exchange, orderType, orderParams);
	}

	private Order placeOrder(int qty, String tradingSymbol, String exchange, String orderType, OrderParams orderParams)
			throws JSONException, IOException, KiteException {
		orderParams.quantity = qty;
		orderParams.tradingsymbol = tradingSymbol;
		orderParams.product = Constants.PRODUCT_NRML;
		orderParams.exchange = exchange;
		orderParams.transactionType = orderType;
		orderParams.validity = Constants.VALIDITY_DAY;
		return kiteConnect.placeOrder(orderParams, Constants.VARIETY_REGULAR);
	}	

	public Map<String, List<Position>> getPositions() throws KiteException, IOException {
		return kiteConnect.getPositions();
	}
	
	public List<Instrument> getInstruments(String exchange, String name, int expiryYear,
			int expiryMonth, int expiryDate) throws JSONException, IOException, KiteException {
		return kiteConnect.getInstruments()
				.stream()
				.filter(i -> i.exchange.equals(exchange))
				.filter(i -> i.name.equals(name))
				.filter(i -> (i.expiry.getYear() == expiryYear - 1900))
				.filter(i -> (i.expiry.getMonth() == expiryMonth - 1))
				.filter(i -> (i.expiry.getDate() == expiryDate))
				.collect(Collectors.toList());
	}
}
