
/**https://leetcode.com/problems/jump-game/**/

public class JumpGame {

	public boolean canJump(int[] nums) {
		int currentIndex = 0;
		int jumpsLeft = nums[currentIndex];
		for (int i = 1; i < nums.length; i++) {
			if (jumpsLeft == 0) {
				return false;
			}

			jumpsLeft = nums[currentIndex] - (i - currentIndex);

			if (nums[i] >= jumpsLeft) {
				currentIndex = i;
				jumpsLeft = nums[i];
			}
		}
		return true;
	}
}
