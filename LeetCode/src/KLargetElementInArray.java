
public class KLargetElementInArray {

	public int findKthLargest(int[] nums, int k) {
		int length = nums.length;
		int pivotPosition = length - k;
		

		return nums[pivotPosition];
	}
}
