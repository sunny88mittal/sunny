package Zerodha.Trader.Core;

import java.io.IOException;

import org.json.JSONException;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.kiteconnect.utils.Constants;
import com.zerodhatech.models.Order;
import com.zerodhatech.models.OrderParams;

public class OrderHandler {

	private KiteConnect kiteConnect;

	public OrderHandler(KiteConnect kiteConnect) {
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
}
