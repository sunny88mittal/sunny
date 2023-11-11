package solution0to100;

public class Solution26 {
	public int removeDuplicates(int[] nums) {
		int luep = 0;
		int k = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[luep]) {
				++luep;
				nums[luep] = nums[i];
				++k;
			}
		}
		return k;
	}
}
