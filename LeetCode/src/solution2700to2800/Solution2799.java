package solution2700to2800;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution2799 {
	public int countCompleteSubarrays(int[] nums) {
		int count = 0;
		int n = nums.length;
		Set<Integer> arrayElementsSet = new HashSet<>();
		Map<Integer, Integer> subArrayElementsMap = new HashMap<>();

		for (int i = 0; i < n; i++) {
			arrayElementsSet.add(nums[i]);
		}

		int start = 0;
		for (int i = 0; i < n; i++) {
			if (subArrayElementsMap.containsKey(nums[i])) {
				subArrayElementsMap.put(nums[i], 1 + subArrayElementsMap.get(nums[i]));
			} else {
				subArrayElementsMap.put(nums[i], 1);
			}
			while (subArrayElementsMap.size() == arrayElementsSet.size()) {
				count += n - i;
				int elementCount = subArrayElementsMap.get(nums[start]);
				if (elementCount == 1) {
					subArrayElementsMap.remove(nums[start]);
				} else {
					subArrayElementsMap.put(nums[start], --elementCount);
				}
				++start;
			}
		}

		return count;
	}
}
