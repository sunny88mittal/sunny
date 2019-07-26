package Entities;

public class CandleStickData {

	public CandleStickData(String timestamp, float open, float high, float low, float close, float volume) {
		this.timestamp = timestamp;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}

	public String timestamp;

	public float open;

	public float high;

	public float low;

	public float close;
	
	public float volume;
}
