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

		//TODO : fix this
		// Rotate the points
		for (int i = 0; i < n; i++) {
			if (x[i] != 0 && y[i] != 0) {
				int count = 1;
				double angle = Math.atan(Math.abs(y[i]) / Math.abs(x[i]));
				for (int j = 0; j < n; j++) {
					if (i != j) {
						double tempyi = Math.abs(y[j]) - Math.abs(y[j]) * Math.sin(angle);
						if (tempyi == 0 || x[j] == 0) {
							count++;
							continue;
						}
						double tempxi = Math.abs(x[j]) - Math.abs(x[j]) * Math.cos(angle);
						if (tempxi == 0 || y[j] == 0) {
							count++;
							continue;
						}
					}
				}
				if (count > result) {
					result = count;
				}
			}
		}

		return result;
	}

	public static void main(String args[]) {
		PlaneGame g = new PlaneGame();
		System.out.println(g.bestShot(new int[] { 0, -1, 1, 1, -1 }, new int[] {
				0, -1, -1, 1, 1 }));
	}
}
