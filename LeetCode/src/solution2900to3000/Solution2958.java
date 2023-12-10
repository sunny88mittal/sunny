package solution2900to3000;

import java.util.HashMap;
import java.util.Map;

public class Solution2958 {
	public int maxSubarrayLength(int[] nums, int k) {
		int maxLength = 0;
		int start = 0;
		Map<Integer, Integer> numCount = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			Integer existingCount = numCount.get(nums[i]);
			if (existingCount == null) {
				existingCount = 0;
			}
			++existingCount;
			numCount.put(nums[i], existingCount);

			if (existingCount > k) {
				int newMaxLength = i - start;
				if (newMaxLength > maxLength) {
					maxLength = newMaxLength;
				}
				while (numCount.get(nums[i]) > k) {
					int count = numCount.get(nums[start]);
					--count;
					numCount.put(nums[start], count);
					++start;
				}
			}
		}

		return Math.max(maxLength, nums.length - start);
	}
}
