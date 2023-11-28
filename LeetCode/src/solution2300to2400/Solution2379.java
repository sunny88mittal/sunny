package solution2300to2400;

public class Solution2379 {
	public int minimumRecolors(String blocks, int k) {
		int n = blocks.length();
		int w = 0;
		int b = 0;
		int minChanges = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			if (blocks.charAt(i) == 'W') {
				++w;
			} else {
				++b;
			}

			if (i < k - 1) {
				continue;
			}

			if (b == k) {
				return 0;
			}

			if (w < minChanges) {
				minChanges = w;
			}

			if (blocks.charAt(i - k + 1) == 'W') {
				--w;
			} else {
				--b;
			}
		}

		return minChanges;
	}
}
