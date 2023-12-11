package solution1200to1300;

import java.util.ArrayList;
import java.util.List;

public class Solution1248 {
	public int numberOfSubarrays(int[] nums, int k) {
		int count = 0;
		int n = nums.length;
		List<Integer> indexList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (nums[i] % 2 == 1) {
				indexList.add(i + 1);
			}
		}

		Integer[] indexes = indexList.toArray(new Integer[0]);

		int startIndex = 0;
		for (int i = k - 1; i < indexes.length; i++) {
			int endIndex = i;
			int startMulitple = startIndex == 0 ? indexes[startIndex] : indexes[startIndex] - indexes[startIndex - 1];
			int endMultiple = endIndex == indexes.length - 1 ? n - indexes[endIndex] + 1
					: indexes[endIndex + 1] - indexes[endIndex];
			count += startMulitple * endMultiple;
			++startIndex;
		}

		return count;
	}
}
