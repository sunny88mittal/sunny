
public class ProductOfArrayExceptSelf {

	public int[] productExceptSelf(int[] nums) {
		int length = nums.length;
		int leftProduct[] = new int[length];
		int rightProduct[] = new int[length];

		leftProduct[0] = 1;
		rightProduct[length - 1] = 1;

		for (int i = 1; i < length; i++) {
			leftProduct[i] = leftProduct[i - 1] * nums[i - 1];
		}

		for (int i = 2; i <= length; i++) {
			rightProduct[length - i] = rightProduct[length - i + 1] * nums[length - i + 1];
		}

		for (int i = 0; i < length; i++) {
			nums[i] = leftProduct[i] * rightProduct[i];
		}

		return nums;
	}
}
