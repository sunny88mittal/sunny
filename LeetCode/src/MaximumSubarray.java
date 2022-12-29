
public class MaximumSubarray {

	public int maxSubArray(int[] nums) {
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] > 0) {
				nums[i] = nums[i] + nums[i - 1];
			}

			if (nums[i] > max) {
				max = nums[i];
			}
		}

		return max;
	}

	public static void main(String args[]) {
		MaximumSubarray obj = new MaximumSubarray();
		obj.maxSubArray(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 });
	}
}
