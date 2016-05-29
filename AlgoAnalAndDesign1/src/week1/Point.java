package week1;

public class Point {
	int x;
	int y;
	
	public Point (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		String s = "(" + x + "," + y + ")";
		return s;
	}
}