
/** https://leetcode.com/problems/jump-game-ii/ **/

public class JumpGame2 {

	public int jump(int[] nums) {
		int jumps[] = new int[nums.length];
		int updatedTill = 0;
		for (int i = 0; i < jumps.length; i++) {
			if (i + nums[i] > updatedTill) {
				int j = updatedTill + 1;
				for (; j <= i + nums[i] && j <= nums.length - 1; j++) {
					jumps[j] = jumps[i] + 1;
				}
				updatedTill = j - 1;
			}
		}
		return jumps[nums.length - 1];
	}

	public static void main(String args[]) {
		JumpGame2 game = new JumpGame2();
		game.jump(new int[] { 2, 1, 1, 1, 1 });
	}
}
