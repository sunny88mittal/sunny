package solution1900to2000;

public class Solution1920 {
	public int[] buildArray(int[] nums) {
		int n = nums.length;
		int[] res = new int[n];
		for (int i = 0; i < n; i++) {
			res[i] = nums[nums[i]];
		}

		return res;
	}
}
