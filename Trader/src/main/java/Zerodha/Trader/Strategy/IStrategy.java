package Zerodha.Trader.Strategy;

public interface IStrategy {
	
	public void initialize();

	public void doNext(double price);
	
	public void disconnectedFromBroker();
}
