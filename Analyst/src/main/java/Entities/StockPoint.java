package Entities;

public class StockPoint {

	public String stock;

	public double value;

	public String pointType;

	@Override
	public String toString() {
		return stock + "	" + value + "	" + pointType;
	}
}
