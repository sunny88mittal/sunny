package SRM146;

public class RectangularGrid {
	
	public long countRectangles(int width, int height) {
		long sum = 0;
		for (int j=1; j<=height; j++) {
			int factor = (height-(j-1));
			for (int k=1; k<=width; k++) {
			    if (j!=k) {
			    	sum += factor * (width-(k-1));	
			    }
			}
		}
		return sum;
	}
}


/**Think about how to calculate using
 * 1. Rectangles + Squares - Squares
 * 2. Using a Formula
 */
