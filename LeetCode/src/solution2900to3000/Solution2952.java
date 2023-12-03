package solution2900to3000;

import java.util.Arrays;

public class Solution2952 {
	public int minimumAddedCoins(int[] coins, int target) {
		int toAdd = 0;
		Arrays.sort(coins);
		int sum = 0;

		for (int i = 0; i < coins.length && sum < target; i++) {
			if (coins[i] > sum + 1) {
				++toAdd;
				sum += (sum + 1);
				--i;
			} else {
				sum += coins[i];
			}
		}

		while (sum < target) {
			sum += (sum + 1);
			++toAdd;
		}

		return toAdd;
	}
}
