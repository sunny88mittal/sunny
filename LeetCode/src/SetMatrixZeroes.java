
public class SetMatrixZeroes {
	public void setZeroes(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;

		boolean isRow = false;
		boolean isCol = false;

		//Check if first row or col should be zeros or not
		for (int i = 0; i < n; i++) {
			isRow |= matrix[0][i] == 0;
		}

		for (int i = 0; i < m; i++) {
			isCol |= matrix[i][0] == 0;
		}

		// Mark in first row and col which row and col will be zeros
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		// Mark all rows and cols except first row and first col
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}

		// Mark first row
		if (matrix[0][0] == 0) {
			if (isCol) {
				for (int i = 0; i < m; i++) {
					matrix[i][0] = 0;
				}
			}

			if (isRow) {
				for (int i = 0; i < n; i++) {
					matrix[0][i] = 0;
				}
			}
		}
	}
}
