
public class UniquePaths {
	public int uniquePaths(int m, int n) {
		int pathsCount[][] = new int[m][n];
		pathsCount[0][0] = 1;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				pathsCount[i][j] += (i - 1) >= 0 ? pathsCount[i - 1][j] : 0;
				pathsCount[i][j] += (j - 1) >= 0 ? pathsCount[i][j - 1] : 0;
			}
		}

		return pathsCount[m - 1][n - 1];
	}

	public static void main(String args[]) {
		UniquePaths obj = new UniquePaths();
		obj.uniquePaths(3, 7);
	}
}
