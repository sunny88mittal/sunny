package solution0to100;

public class Solution85 {
	public int maximalRectangle(char[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;

		int[][] rowComp = new int[rows][cols];
		int[][] colComp = new int[rows][cols];
		int max = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i == 0 || matrix[i][j] == '0') {
					colComp[i][j] = matrix[i][j] - '0';
				} else if (matrix[i][j] == '1') {
					colComp[i][j] = 1 + colComp[i - 1][j];
				}

				if (j == 0 || matrix[i][j] == '0') {
					rowComp[i][j] = matrix[i][j] - '0';
				} else if (matrix[i][j] == '1') {
					rowComp[i][j] = 1 + rowComp[i][j - 100];
				}

				int currentSize = colComp[i][j] * rowComp[i][j];
				max = currentSize > max ? currentSize : max;
			}
		}

		return max;
	}
}
