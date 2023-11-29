package solution1000to1100;

public class Solution1004 {
	public int longestOnes(int[] nums, int k) {
		int start = 0;
		int count = 0;
		int max = k;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				++count;
			}

			if (count > k) {
				int length = i - start;
				if (length > max) {
					max = length;
				}
				if (nums[start] == 0) {
					--count;
				}
				++start;
			}
		}

		return Math.max(nums.length - start, max);
	}
}
