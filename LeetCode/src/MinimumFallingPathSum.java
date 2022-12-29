
public class MinimumFallingPathSum {

	public int minFallingPathSum(int[][] matrix) {
		int minimum = Integer.MAX_VALUE;
		int rows = matrix.length;
		int cols = matrix[0].length;
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int top = (i - 1 >= 0) ? matrix[i - 1][j] : Integer.MAX_VALUE;
				int topLeft = (i - 1 >= 0) && (j - 1 >= 0) ? matrix[i - 1][j - 1] : Integer.MAX_VALUE;
				int topRight = (i - 1 >= 0) && (j + 1 < cols) ? matrix[i - 1][j + 1] : Integer.MAX_VALUE;
				int min = Math.min(top, Math.min(topLeft, topRight));
				matrix[i][j] += min;
			}
		}

		for (int j = 0; j < cols; j++) {
			if (matrix[rows - 1][j] < minimum) {
				minimum = matrix[rows - 1][j];
			}
		}

		return minimum;
	}
}
