package solution1600to1700;

public class Solution1685 {
	public int[] getSumAbsoluteDifferences(int[] nums) {
		int n = nums.length;
		int[] preSum = new int[n];
		int[] postSum = new int[n];
		int[] result = new int[n];

		for (int i = 0; i < n; i++) {
			if (i == 0) {
				preSum[i] = nums[0];
				postSum[n - 1] = nums[n - 1];
			} else {
				preSum[i] = preSum[i - 1] + nums[i];
				postSum[n - i - 1] = postSum[n - i] + nums[n - i - 1];
			}
		}

		for (int i = 0; i < n; i++) {
			result[i] = (i + 1) * nums[i] - preSum[i] + postSum[i] - (n - i) * nums[i];
		}

		return result;
	}
}
