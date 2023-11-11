package solution0to100;

import java.util.Arrays;

public class Solution35 {
	public int searchInsert(int[] nums, int target) {
		int pos = Arrays.binarySearch(nums, target);
		if (pos >= 0) {
			return pos;
		}
		return pos * -1 - 1;
	}
}
