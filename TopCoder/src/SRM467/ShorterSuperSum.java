package SRM467;

public class ShorterSuperSum {

	private int[][] dp = new int[15][15];

	public int calculate(int k, int n) {
		if (k == 0) {
			return n;
		}

		int sum = 0;
		for (int i = 1; i <= n; i++) {
			if (dp[k - 1][i] == 0) {
				dp[k - 1][i] = calculate(k - 1, i);
			}
			sum += dp[k - 1][i];
		}

		return sum;
	}
}
