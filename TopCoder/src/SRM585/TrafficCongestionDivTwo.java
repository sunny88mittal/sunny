package SRM585;

public class TrafficCongestionDivTwo {

	public long theMinCars(int treeHeight) {
		long minCars = 0;
		int arraysize = treeHeight + 1;
		if (treeHeight < 2) {
			minCars = 1;
		} else {
			long[] minCarsArray = new long[arraysize];

			minCarsArray[0] = 1;
			minCarsArray[1] = 1;

			for (int i = 2; i < arraysize; i++) {
				int toStop = i - 2;
				long sum = 0;
				for (int j = 0; j <= toStop; j++) {
					sum += minCarsArray[j];
				}
				minCarsArray[i] = 2 * sum + 1;
			}

			minCars = minCarsArray[treeHeight];
		}

		return minCars;
	}
}

//Check if better solution possible
