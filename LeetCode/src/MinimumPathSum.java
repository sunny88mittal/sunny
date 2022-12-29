
public class MinimumPathSum {
	public int minPathSum(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				int s1 = (i - 1) >= 0 ? grid[i - 1][j] : Integer.MAX_VALUE;
				int s2 = (j - 1) >= 0 ? grid[i][j - 1] : Integer.MAX_VALUE;
				grid[i][j] += Math.min(s1, s2);
			}
		}

		return grid[m - 1][n - 1];
	}
}
