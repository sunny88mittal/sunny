package SRM667;

public class OrderOfOperationsDiv2 {

	public int minTime(String[] s) {
		int fmask = 0;
		int n = s.length;
		int m = s[0].length();
		int[] inst = new int[n];

		// Convert the string to integer mask
		for (int i = 0; i < n; i++) {
			int insti = 0;
			for (int j = 0; j < m; j++) {
				if (s[i].charAt(j) == '1') {
					insti |= 1 << j;
				}
			}
			inst[i] = insti;
			fmask |= insti;
		}

		// Find the min time taken
		int length = 1 << m;
		int dp[] = new int[length];
		for (int i = 0; i < length; i++) {
			dp[i] = Integer.MAX_VALUE / 2;
		}
		dp[0] = 0;

		for (int mask = 0; mask < length; mask++) {
			for (int j = 0; j < n; j++) {
				int nmask = mask | inst[j];
				int changes = nmask - mask;
				int k = Integer.bitCount(changes);
				dp[nmask] = Math.min(dp[nmask], dp[mask] + k * k);
			}
		}

		return dp[fmask];
	}
}