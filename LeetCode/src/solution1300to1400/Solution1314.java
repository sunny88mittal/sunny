package solution1300to1400;

public class Solution1314 {
	int[][] prefixSum;

	public int[][] matrixBlockSum(int[][] mat, int k) {
		int m = mat.length;
		int n = mat[0].length;
		int[][] sol = new int[m][n];
		prefixSum = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1]
						+ mat[i - 1][j - 1];
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				sol[i][j] = getSum(i + k + 1, j + k + 1, m, n) - getSum(i + k + 1, j - k, m, n)
						- getSum(i - k, j + k + 1, m, n) + getSum(i - k, j - k, m, n);
			}
		}

		return sol;
	}

	private int getSum(int row, int col, int m, int n) {
		row = Math.max(0, Math.min(row, m));
		col = Math.max(0, Math.min(col, n));
		return prefixSum[row][col];
	}
}
