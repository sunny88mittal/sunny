package week1;

import java.util.Comparator;

public class XComparator implements Comparator<Point> {
	@Override
	public int compare(Point o1, Point o2) {
		Integer o1x = o1.x;
		Integer o2x = o2.x;
		return o1x.compareTo(o2x);
	}
}
