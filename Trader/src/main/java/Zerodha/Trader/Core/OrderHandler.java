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

	public Order placeOrder(int qty, String tradingSymbol, String exchange, String orderType)
			throws JSONException, IOException, KiteException {
		OrderParams orderParams = new OrderParams();
		orderParams.quantity = qty;
		orderParams.orderType = Constants.ORDER_TYPE_MARKET;
		orderParams.tradingsymbol = tradingSymbol;
		orderParams.product = Constants.PRODUCT_NRML;
		orderParams.exchange = exchange;
		orderParams.transactionType = orderType;
		orderParams.validity = Constants.VALIDITY_DAY;
		
		return kiteConnect.placeOrder(orderParams, Constants.VARIETY_REGULAR);
	}
}
