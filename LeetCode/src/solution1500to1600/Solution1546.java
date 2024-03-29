package solution1500to1600;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution1546 {
	public static int maxNonOverlapping(int[] nums, int target) {
		// Ending the subarray ASAP always has a better result.
		int ans = 0;
		int prefix = 0;
		Set<Integer> prefixes = new HashSet<>(Arrays.asList(0));

		// Greedily find the subarrays that equal to the target.
		for (int num : nums) {
			prefix += num;
			// Check if there is a subarray ends here and equals to the target.
			if (prefixes.contains(prefix - target)) {
				// Find one and discard all the prefixes that have been used.
				ans++;
				prefix = 0;
				prefixes = new HashSet<>(Arrays.asList(0));
			} else {
				prefixes.add(prefix);
			}
		}

		return ans;
	}
}
