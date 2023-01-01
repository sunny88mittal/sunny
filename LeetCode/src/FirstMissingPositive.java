
public class FirstMissingPositive {
	public int firstMissingPositive(int[] nums) {
		// Put the no's in correct place
		for (int i = 0; i < nums.length; i++) {
			boolean changed = true;
			while (changed) {
				if (nums[i] > 0 && nums[i] <= nums.length && i + 1 != nums[i] && nums[nums[i] - 1] != nums[i]) {
					int temp = nums[nums[i] - 1];
					nums[nums[i] - 1] = nums[i];
					nums[i] = temp;
				} else {
					changed = false;
				}
			}
		}

		//Check the missing no
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				return i + 1;
			}
		}

		return nums.length + 1;
	}

	public static void main(String args[]) {
		FirstMissingPositive obj = new FirstMissingPositive();
		int[] nums = new int[] { 1, 1 };
		obj.firstMissingPositive(nums);
	}
}
