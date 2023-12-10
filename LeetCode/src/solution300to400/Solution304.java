package solution300to400;

public class Solution304 {

	private int[][] prefixMatrix;

	public void NumMatrix(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;

		prefixMatrix = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				prefixMatrix[i][j] = prefixMatrix[i - 1][j] + prefixMatrix[i][j - 1] - prefixMatrix[i - 1][j - 1]
						+ matrix[i - 1][j - 1];
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		return prefixMatrix[row2 + 1][col2 + 1] - prefixMatrix[row2 + 1][col1] - prefixMatrix[row1][col2 + 1]
				+ prefixMatrix[row1][col1];
	}
}
