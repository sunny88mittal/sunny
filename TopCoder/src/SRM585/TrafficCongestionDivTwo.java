package SRM585;

public class TrafficCongestionDivTwo {

	public long theMinCars(int treeHeight) {
		long[] minCarsArray = new long[treeHeight + 1];

		minCarsArray[0] = 1;
		minCarsArray[1] = 1;

		for (int i = 2; i <= treeHeight; i++) {
			minCarsArray[i] = 1;
			for (int j = 0; j <= i - 2; j++) {
				minCarsArray[i] += 2 * minCarsArray[j];
			}
		}

		return minCarsArray[treeHeight];
	}
}