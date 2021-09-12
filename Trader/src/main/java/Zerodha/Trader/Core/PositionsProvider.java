package Zerodha.Trader.Core;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Position;

public class PositionsProvider {

	private KiteConnect kiteConnect;

	public PositionsProvider(KiteConnect kiteConnect) {
		super();
		this.kiteConnect = kiteConnect;
	}

	public Map<String, List<Position>> getPositions() throws KiteException, IOException {
		return kiteConnect.getPositions();
	}
}
