package solution0to100;

public class Solution80 {
	public int removeDuplicates(int[] nums) {
		int k = 0;
		for (int num : nums) {
			if (k < 2 || num > nums[k - 2]) {
				nums[k] = num;
				++k;
			}
		}
		return k;
	}
}
