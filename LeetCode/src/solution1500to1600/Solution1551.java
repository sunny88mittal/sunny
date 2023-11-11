package solution1500to1600;

public class Solution1551 {
	public int minOperations(int n) {
		int count = 0;
		int left = 0;
		int right = n - 1;

		while (left < right) {
			count += ((2 * right + 1) - (2 * left + 1)) / 2;
			++left;
			--right;
		}

		return count;
	}
}
