package solution0to100;

import java.util.ArrayList;
import java.util.List;

public class Solution54 {

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> response = new ArrayList<>();
		int m = matrix.length;
		int n = matrix[0].length;

		int k = 0, l = 0;

		while (k < m && l < n) {
			// Print the first row from the remaining rows
			for (int i = l; i < n; ++i) {
				response.add(matrix[k][i]);
			}
			k++;

			// Print the last column from the remaining
			// columns
			for (int i = k; i < m; ++i) {
				response.add(matrix[i][n - 1]);
			}
			n--;

			// Print the last row from the remaining rows */
			if (k < m) {
				for (int i = n - 1; i >= l; --i) {
					response.add(matrix[m - 1][i]);
				}
				m--;
			}

			// Print the first column from the remaining
			// columns */
			if (l < n) {
				for (int i = m - 1; i >= k; --i) {
					response.add(matrix[i][l]);
				}
				l++;
			}
		}

		return response;
	}
}
