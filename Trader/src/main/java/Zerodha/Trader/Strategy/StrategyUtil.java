package Zerodha.Trader.Strategy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.kiteconnect.utils.Constants;
import com.zerodhatech.models.Order;
import com.zerodhatech.models.Position;

import Zerodha.Trader.Core.AppConstants;
import Zerodha.Trader.Core.KiteUser;
import Zerodha.Trader.Core.TradingSymbolHelper;
import Zerodha.Trader.Logging.Logger;
import Zerodha.Trader.Services.KiteHandler;

public class StrategyUtil {

	public static Order placeNRMLOrders(KiteUser kiteUser, int qty, String optionSymbol, String transactionType,
			boolean checkforExistingPosition) throws Throwable {
		KiteHandler kiteHandler = kiteUser.kiteHandler;
		try {

			if (!isPositionOpen(optionSymbol, kiteHandler) && checkforExistingPosition) {
				System.out.println("Position already squared off for :" + optionSymbol);
				return null;
			}

			Order order = kiteHandler.placeMarketOrder(qty, optionSymbol, Constants.EXCHANGE_NFO, transactionType);
			Logger.print(StrategyUtil.class, qty + ":" + optionSymbol + ":" + transactionType);
			return order;
		} catch (Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void doCheckPointing(String data, String fileName) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(data);
			writer.close();
		} catch (IOException e) {
			Logger.print(StrategyUtil.class, "Error in adding check point");
		}
	}

	public static boolean isPositionOpen(String tradingSymbol, KiteHandler kiteHandler)
			throws IOException, KiteException {
		List<Position> positions = kiteHandler.getPositions().get("net");
		for (Position position : positions) {
			if (Math.abs(position.netQuantity) > 0 && position.tradingSymbol.equals(tradingSymbol)) {
				return true;
			}
		}
		return false;
	}

	public static String getPutOptionSymbol(int strike, String optionDateValue) {
		return getOptionSymbol(strike, AppConstants.OPTION_TYPE_PE, optionDateValue);
	}

	public static String getCallOptionSymbol(int strike, String optionDateValue) {
		return getOptionSymbol(strike, AppConstants.OPTION_TYPE_CE, optionDateValue);
	}

	public static String getOptionSymbol(int strike, String type, String optionDateValue) {
		return TradingSymbolHelper.getFullOptionSymbol(AppConstants.INDEX_BANKNIFTY, optionDateValue, strike, type);
	}

	public static int getStrikeToTrade(double price) {
		int strike = (int) (price - price % 100);
		strike += price % 100 > 50 ? 100 : 0;
		return strike;
	}
}
