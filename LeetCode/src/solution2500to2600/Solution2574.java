package solution2500to2600;

public class Solution2574 {
	public int[] leftRightDifference(int[] nums) {
		int total = 0;
		int leftSum = 0;
		int n = nums.length;
		int result[] = new int[n];

		for (int num : nums) {
			total += num;
		}

		for (int i = 0; i < n; i++) {
			result[i] = Math.abs(leftSum - (total - nums[i]));
			leftSum += nums[i];
			total -= nums[i];
		}

		return result;
	}
}
