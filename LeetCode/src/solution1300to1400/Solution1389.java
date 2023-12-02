package solution1300to1400;

import java.util.ArrayList;
import java.util.List;

public class Solution1389 {
	public int[] createTargetArray(int[] nums, int[] index) {
		int n = nums.length;
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			result.add(index[i], nums[i]);
		}

		int finalResult[] = new int[n];
		for (int i = 0; i < n; i++) {
			finalResult[i] = result.get(i);
		}

		return finalResult;
	}
}
