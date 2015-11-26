package SRM667;

public class PointDistance {

	public int[] findPoint(int x1, int y1, int x2, int y2) {
	   	for (int i=-100; i<=100; i++) {
	   		if (i  == x1 || i == x2) {
	   			continue;
	   		}
	   		for (int j=-100; j<=100; j++) {
	   			if (j  == y1 || i == y2) {
		   			continue;
		   		}
	   			int d1 = getDistance(x1, y1, i, j);
	   			int d2 = getDistance(x2, y2, i, j);
	   			if (d1 > d2) {
	   				return new int[] {i,j};
	   			}
	   		}
	   	}
	   	return null;
	}	
	
	private int getDistance(int x1, int y1, int x2, int y2) {
		return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
	}
}
