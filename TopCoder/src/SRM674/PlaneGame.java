package SRM674;

public class PlaneGame {

	public int bestShot(int[] x, int[] y) {
		int result = -1;
		int n = x.length;

		// X- axis shift
		for (int i = 0; i < n; i++) {
			int xi = x[i];
			int count = 0;
			for (int j = 0; j < n; j++) {
				if (x[j] - xi == 0 || y[j] == 0) {
					count++;
				}
			}
			if (count > result) {
				result = count;
			}
		}

		// Y-axis shift
		for (int i = 0; i < n; i++) {
			int yi = y[i];
			int count = 0;
			for (int j = 0; j < n; j++) {
				if (y[j] - yi == 0 || x[j] == 0) {
					count++;
				}
			}
			if (count > result) {
				result = count;
			}
		}

		//TODO :: Finish this code
		// Rotate the points
		for (int i = 0; i < n; i++) {
			int count = 0;
			if (x[i] != 0 || y[i] != 0) {
				for (int j = 0; j < n; j++) {
					if (count > result) {
						result = count;
					}
				}	
			}
		}

		return result;
	}

	public static void main(String args[]) {
		PlaneGame g = new PlaneGame();
		System.out.println(g.bestShot(new int[] { 0, 5 }, new int[] { 0, 5 }));
	}
}
