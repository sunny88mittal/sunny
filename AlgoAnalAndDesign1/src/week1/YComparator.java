package week1;

import java.util.Comparator;

public class YComparator implements Comparator<Point> {
	@Override
	public int compare(Point o1, Point o2) {
		Integer o1y = o1.y;
		Integer o2y = o2.y;
		return o1y.compareTo(o2y);
	}
}