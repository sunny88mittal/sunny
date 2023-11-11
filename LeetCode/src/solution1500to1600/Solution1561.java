package solution1500to1600;

import java.util.Arrays;

public class Solution1561 {
	public int maxCoins(int[] piles) {
		int sum = 0;
		Arrays.sort(piles);
		int n = piles.length;

		int left = 0;
		int right = n - 1;

		while (left < right) {
			sum += piles[right - 1];
			++left;
			right -= 2;
		}

		return sum;
	}
}
