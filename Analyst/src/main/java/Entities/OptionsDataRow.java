package Entities;

public class OptionsDataRow {

	public static enum OptionType {
		CALL, PUT
	}

	public int strikePrice;

	public int openInterestChange;

	public int volume;

	public float IV;

	public float LTP;

	public float netChange;

	public int bidQty;

	public float bidPrice;

	public int askQty;

	public float askPrice;
}