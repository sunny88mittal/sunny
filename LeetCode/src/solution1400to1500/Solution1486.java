package solution1400to1500;

public class Solution1486 {
	public int xorOperation(int n, int start) {
		int finalXor = start;

		for (int i = 1; i < n; i++) {
			finalXor ^= (2 * i + start);
		}

		return finalXor;
	}
}
