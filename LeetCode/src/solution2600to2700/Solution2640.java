package solution2600to2700;

public class Solution2640 {
	public long[] findPrefixScore(int[] nums) {
		int n = nums.length;
		long ans[] = new long[n];
		ans[0] = nums[0];
		for (int i = 1; i < n; i++) {
			ans[i] = Math.max(ans[i - 1], nums[i]);
		}

		ans[0] += (long) nums[0];
		for (int i = 1; i < n; i++) {
			ans[i] += ans[i - 1] + (long) nums[i];
		}

		return ans;
	}
}
