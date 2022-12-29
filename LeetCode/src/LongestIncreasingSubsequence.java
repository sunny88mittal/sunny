
public class LongestIncreasingSubsequence {

	public int lengthOfLIS(int[] nums) {
		int length = nums.length;
		int count[] = new int[nums.length];
		int max = 1;
		count[length - 1] = 1;

		for (int i = nums.length - 2; i >= 0; i--) {
			count[i] = 1;
			for (int j = i + 1; j < nums.length; j++) {
				if ((nums[i] < nums[j]) && (count[i] < 1 + count[j])) {
					count[i] = 1 + count[j];
				}
				if (max < count[i]) {
					max = count[i];
				}
			}
		}

		return max;
	}

	public static void main(String args[]) {
		int nums[] = new int[] { 10, 9, 2, 5, 3, 7, 101, 18 };
		LongestIncreasingSubsequence obj = new LongestIncreasingSubsequence();
		obj.lengthOfLIS(nums);
	}
}
