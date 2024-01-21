package solution1400to1500;

import java.util.Arrays;

public class Solution1402 {
	public int maxSatisfaction(int[] satisfaction) {
		Arrays.sort(satisfaction);
		int n = satisfaction.length;
		if (satisfaction[n - 1] < 0) {
			return 0;
		}

		int positiveElementSum = 0;
		int positiveElementStart = -1;
		int totalSatisfcation = 0;
		int k = 0;
		for (int i = 0; i < n; i++) {
			if (satisfaction[i] >= 0) {
				if (positiveElementStart < 0) {
					positiveElementStart = i;
				}
				positiveElementSum += satisfaction[i];
				++k;
				totalSatisfcation += k * satisfaction[i];
			}
		}

		for (int i = positiveElementStart - 1; i >= 0; i--) {
			if (satisfaction[i] + positiveElementSum >= 0) {
				totalSatisfcation += satisfaction[i] + positiveElementSum;
				positiveElementSum += satisfaction[i];
			} else {
				break;
			}
		}

		return totalSatisfcation;
	}
}
