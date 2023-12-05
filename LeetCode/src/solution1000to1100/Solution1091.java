package solution1000to1100;

import java.util.LinkedList;
import java.util.Queue;

public class Solution1091 {
	public int shortestPathBinaryMatrix(int[][] grid) {
		int n = grid.length;
		if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
			return -1;
		}

		int[][] count = new int[n][n];
		boolean visited[][] = new boolean[n][n];
		count[0][0] = 1;

		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { 0, 0 });
		while (!q.isEmpty()) {
			int[] cell = q.remove();
			int i = cell[0];
			int j = cell[1];
			visited[i][j] = true;
			int currentCount = count[i][j];
			if (i - 1 >= 0 && !visited[i - 1][j] && grid[i - 1][j] == 0) {
				count[i - 1][j] = 1 + currentCount;
				q.add(new int[] { i - 1, j });
				visited[i - 1][j] = true;
			}

			if (j - 1 >= 0 && !visited[i][j - 1] && grid[i][j - 1] == 0) {
				count[i][j - 1] = 1 + currentCount;
				q.add(new int[] { i, j - 1 });
				visited[i][j - 1] = true;
			}

			if (i + 1 < n && !visited[i + 1][j] && grid[i + 1][j] == 0) {
				count[i + 1][j] = 1 + currentCount;
				q.add(new int[] { i + 1, j });
				visited[i + 1][j] = true;
			}

			if (j + 1 < n && !visited[i][j + 1] && grid[i][j + 1] == 0) {
				count[i][j + 1] = 1 + currentCount;
				q.add(new int[] { i, j + 1 });
				visited[i][j + 1] = true;
			}

			if (i - 1 >= 0 && j - 1 >= 0 && !visited[i - 1][j - 1] && grid[i - 1][j - 1] == 0) {
				count[i - 1][j - 1] = 1 + currentCount;
				q.add(new int[] { i - 1, j - 1 });
				visited[i - 1][j - 1] = true;
			}

			if (i - 1 >= 0 && j + 1 < n && !visited[i - 1][j + 1] && grid[i - 1][j + 1] == 0) {
				count[i - 1][j + 1] = 1 + currentCount;
				q.add(new int[] { i - 1, j + 1 });
				visited[i - 1][j + 1] = true;
			}

			if (i + 1 < n && j - 1 >= 0 && !visited[i + 1][j - 1] && grid[i + 1][j - 1] == 0) {
				count[i + 1][j - 1] = 1 + currentCount;
				q.add(new int[] { i + 1, j - 1 });
				visited[i + 1][j - 1] = true;
			}

			if (i + 1 < n && j + 1 < n && !visited[i + 1][j + 1] && grid[i + 1][j + 1] == 0) {
				count[i + 1][j + 1] = 1 + currentCount;
				q.add(new int[] { i + 1, j + 1 });
				visited[i + 1][j + 1] = true;
			}

		}

		if (count[n - 1][n - 1] < n) {
			return -1;
		}

		return count[n - 1][n - 1];
	}

	public static void main(String args[]) {
		int[][] grid = new int[][] { { 0, 1, 1, 1, 1, 1, 1, 1 }, { 0, 1, 1, 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 1, 1, 1, 0 },
				{ 0, 1, 0, 1, 1, 1, 1, 0 }, { 0, 1, 1, 0, 0, 1, 1, 0 }, { 0, 1, 1, 1, 1, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 1, 1, 0 }, { 1, 1, 1, 1, 1, 1, 1, 0 } };
		Solution1091 obj = new Solution1091();
		obj.shortestPathBinaryMatrix(grid);
	}
}
