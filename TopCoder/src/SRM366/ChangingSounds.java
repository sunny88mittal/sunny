package SRM366;

public class ChangingSounds {

	public int maxFinal(int[] changeIntervals, int beginLevel, int maxLevel) {
		boolean[][] dp = new boolean[changeIntervals.length + 1][maxLevel + 1];

		dp[0][beginLevel] = true;

		for (int i = 0; i < changeIntervals.length; i++) {
			for (int j = 0; j < maxLevel; j++) {
				if (dp[i][j]) {
					if (j + changeIntervals[i] <= maxLevel) {
						dp[i + 1][j + changeIntervals[i]] = true;
					}
					if (j - changeIntervals[i] >= 0) {
						dp[i + 1][j - changeIntervals[i]] = true;
					}
				}
			}
		}

		int max = -1;
		for (int j = 0; j <= maxLevel; j++) {
			if (dp[changeIntervals.length][j]) {
				max = j;
			}
		}

		return max;
	}
}
