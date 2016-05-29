package week3;

public class EndPointWithCount {
	
	public short endPoint;
	
	public short count =1;
	
	public EndPointWithCount(short endPoint) {
		this.endPoint = endPoint;
	}
	
	public EndPointWithCount(short endPoint, short count) {
		this.endPoint = endPoint;
		this.count = count;
	}
}
