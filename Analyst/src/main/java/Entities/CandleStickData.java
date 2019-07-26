package Entities;

public class CandleStickData {

	@Override
	public String toString() {
		return "CandleStickData [timestamp=" + timestamp + ", open=" + open + ", high=" + high + ", low=" + low
				+ ", close=" + close + ", volume=" + volume + "]";
	}

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
