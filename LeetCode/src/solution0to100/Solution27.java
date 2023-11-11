package solution0to100;

public class Solution27 {
	public int removeElement(int[] nums, int val) {
		int k = 0;
		int loc = -1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == val) {
				if (loc == -1) {
					loc = i;
				}
			}

			if (nums[i] != val) {
				++k;
				if (loc != -1) {
					nums[loc] = nums[i];
					++loc;
				}
			}
		}

		return k;
	}
}
