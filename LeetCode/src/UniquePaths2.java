
public class UniquePaths2 {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int pathsCount[][] = new int[m][n];
		pathsCount[0][0] = 1;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				pathsCount[i][j] += (i - 1) >= 0 ? pathsCount[i - 1][j] : 0;
				pathsCount[i][j] += (j - 1) >= 0 ? pathsCount[i][j - 1] : 0;
				if (obstacleGrid[i][j] == 1) {
					pathsCount[i][j] = 0;
				}
			}
		}

		return pathsCount[m - 1][n - 1];
	}
}
