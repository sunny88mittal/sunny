package solution2900to3000;

public class Solution2962 {
	public long countSubarrays(int[] nums, int k) {
		int maxi = 0, count = 0;

		// Get max
		for (int i = 0; i < nums.length; i++) {
			maxi = Math.max(maxi, nums[i]);
		}

		// Count the sub arrays using sliding window
		long ans = 0;
		int j = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == maxi) {
				++count;
			}
			// count sub arrays for all indexes for which count is >=k
			// count will be equal to remaining characters
			while (count >= k) {
				ans += (long) (nums.length - i);
				if (nums[j] == maxi) {
					--count;
				}
				++j;
			}
		}

		return ans;
	}
}
