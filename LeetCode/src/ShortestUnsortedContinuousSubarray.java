
public class ShortestUnsortedContinuousSubarray {

	public int findUnsortedSubarray(int[] nums) {
		int start = Integer.MAX_VALUE;
		int end = Integer.MIN_VALUE;
		boolean found = false;

		for (int i = 1; i < nums.length; i++) {
			int j = i;
			while (j > 0 && nums[j - 1] > nums[j]) {
				found = true;
				int temp = nums[j];
				nums[j] = nums[j - 1];
				nums[j - 1] = temp;
				if (j - 1 < start) {
					start = j - 1;
				}
				if (j > end) {
					end = j;
				}
				--j;
			}
		}

		return found ? (end - start + 1) : 0;
	}
}
