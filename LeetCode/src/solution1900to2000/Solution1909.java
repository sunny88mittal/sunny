package solution1900to2000;

public class Solution1909 {
	public boolean canBeIncreasing(int[] nums) {
		int count = 0;
		int index = -1;

		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i + 1] <= nums[i]) {
				++count;
				index = i;
			}
		}

		if (count == 0) {
			return true;
		}

		if (count == 1) {
			//We can leave first or last element here 
			if (index == 0 || index == nums.length - 2) {
				return true;
			}

			if (nums[index + 1] > nums[index - 1]) {
				return true;
			}

			++index;
			if (nums[index + 1] > nums[index - 1]) {
				return true;
			}
		}

		return false;
	}
}
