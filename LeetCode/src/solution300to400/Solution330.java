package solution300to400;

public class Solution330 {
	public int minPatches(int[] nums, int n) {
		int toAdd = 0;
		long sum = 0;
		long target = n;

		for (int i = 0; i < nums.length && sum < target; i++) {
			if (nums[i] > sum + 1) {
				++toAdd;
				sum += (sum + 1);
				--i;
			} else {
				sum += (long) nums[i];
			}
		}

		while (sum < target) {
			sum += (sum + 1);
			++toAdd;
		}

		return toAdd;
	}
}
