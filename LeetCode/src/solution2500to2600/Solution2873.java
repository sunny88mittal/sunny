package solution2500to2600;

public class Solution2873 {
	public long maximumTripletValue(int[] nums) {
		long max = Long.MIN_VALUE;
		int n = nums.length;

		for (int i = 0; i < n - 2; i++) {
			for (int j = i + 1; j < n - 1; j++) {
				for (int k = j + 1; k < n; k++) {
					long newValue = ((long) nums[i] - (long) nums[j]) * (long) nums[k];
					if (newValue > max) {
						max = newValue;
					}
				}
			}
		}

		return max < 0 ? 0 : max;
	}
}
