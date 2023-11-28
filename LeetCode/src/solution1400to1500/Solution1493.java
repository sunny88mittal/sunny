package solution1400to1500;

public class Solution1493 {
	public int longestSubarray(int[] nums) {
		int longestSubarray = 0;
		int startingPoint = 0;
		int firstZeroAt = -1;
		int zeros = nums[0] == 0 ? 1 : 0;
		if (nums[0] == 0) {
			firstZeroAt = 0;
		}
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == 0 && zeros == 1) {
				int length = i - startingPoint - 1;
				if (length > longestSubarray) {
					longestSubarray = length;
				}
				if (firstZeroAt != -1) {
					startingPoint = firstZeroAt + 1;
				} else {
					startingPoint += 1;
				}
				firstZeroAt = i;
				continue;
			}

			if (nums[i] == 0) {
				zeros = 1;
				if (firstZeroAt == -1) {
					firstZeroAt = i;
				}
			}
		}

		int length = nums.length - startingPoint - 1;
		if (length > longestSubarray) {
			longestSubarray = length;
		}

		return longestSubarray;
	}
}
