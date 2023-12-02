package solution2400to2500;

public class Solution2498 {
	public int maxJump(int[] stones) {
		int min = stones[1] - stones[0];
		for (int i = 0; i < stones.length; i++) {
			if (i + 2 < stones.length) {
				min = Math.min(min, stones[i + 2] - stones[i]);
			}
		}
		return min;
	}
}
