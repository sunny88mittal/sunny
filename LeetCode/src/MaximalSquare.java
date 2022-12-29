
public class MaximalSquare {
	public int maximalSquare(char[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;

		int dp[][] = new int[rows][cols];
		int max = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int currentValue = Integer.parseInt(matrix[i][j] + "");
				if (currentValue == 0) {
					dp[i][j] = 0;
					continue;
				}
				int left = (j - 1 < 0) ? 0 : dp[i][j - 1];
				int topLeft = (i - 1 < 0 || j - 1 < 0) ? 0 : dp[i - 1][j - 1];
				int top = (i - 1 < 0) ? 0 : dp[i - 1][j];
				int count = Math.min(top, Math.min(topLeft, left));
				dp[i][j] = count + currentValue;
				if (dp[i][j] > max) {
					max = dp[i][j];
				}
			}
		}

		return max * max;
	}

	public static void main(String args[]) {
		char[][] matrix = { { '1', '1' }, { '1', '1' } };
		MaximalSquare obj = new MaximalSquare();
		obj.maximalSquare(matrix);
	}
}
