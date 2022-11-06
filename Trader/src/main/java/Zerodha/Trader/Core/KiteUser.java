package Zerodha.Trader.Core;

public class KiteUser {

	public int qty;

	public String name;

	public KiteHandler kiteHandler;

	public KiteUser(int qty, String name, KiteHandler kiteHandler) {
		super();
		this.qty = qty;
		this.name = name;
		this.kiteHandler = kiteHandler;
	}
}