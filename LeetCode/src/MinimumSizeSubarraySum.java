
public class MinimumSizeSubarraySum {

	public int minSubArrayLen(int target, int[] nums) {
		int minCount = Integer.MAX_VALUE;
		int sum = 0;
		int j = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			while (sum >= target && j <= i) {
				minCount = Math.min(minCount, i + 1 - j);
				sum -= nums[j];
				++j;
			}
		}

		return minCount == Integer.MAX_VALUE ? 0 : minCount;
	}

	public static void main(String args[]) {
		int nums[] = new int[] { 2, 3, 1, 2, 4, 3 };
		MinimumSizeSubarraySum obj = new MinimumSizeSubarraySum();
		obj.minSubArrayLen(7, nums);
	}
}
