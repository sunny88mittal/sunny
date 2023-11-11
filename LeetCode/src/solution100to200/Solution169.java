package solution100to200;

public class Solution169 {
	public int majorityElement(int[] nums) {
		Integer majorityElement = null;
		int count = 0;
		for (int num : nums) {
			if (majorityElement == null || num == majorityElement) {
				majorityElement = num;
				++count;
			} else {
				--count;
			}

			if (count == 0) {
				majorityElement = null;
			}
		}
		return majorityElement;
	}
}
