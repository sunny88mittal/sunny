package solution1300to1400;

public class Solution1365 {
	public int[] smallerNumbersThanCurrent(int[] nums) {
		int n = nums.length;
		int[] count = new int[n];

		for (int i = 0; i < n; i++) {
			int ccount = 0;
			for (int j = 0; j < n; j++) {
				if (nums[i] < nums[j]) {
					++ccount;
				}
			}
			count[i] = ccount;
		}

		return count;
	}
}
