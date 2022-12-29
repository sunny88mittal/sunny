import java.util.Arrays;

public class CombinationSum4 {
	public int combinationSum4(int[] nums, int target) {
		int[] combinations = new int[target + 1];
		combinations[0] = 1;
		Arrays.sort(nums);

		for (int i = 0; i <= target; i++) {
			for (int j = 0; j < nums.length; j++) {
				if (i - nums[j] >= 0) {
					combinations[i] += combinations[i - nums[j]];
				}
			}
		}

		return combinations[target];
	}
}
