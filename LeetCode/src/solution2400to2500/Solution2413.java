package solution2400to2500;

public class Solution2413 {
	public int smallestEvenMultiple(int n) {
		if (n % 2 == 0) {
			return n;
		}

		return 2 * n;
	}
}
