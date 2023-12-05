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
		int[][] visits = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 },
				{ 1, 1 } };

		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { 0, 0 });
		while (!q.isEmpty()) {
			int[] cell = q.remove();
			int i = cell[0];
			int j = cell[1];
			visited[i][j] = true;
			int currentCount = count[i][j];
			for (int k = 0; k < visits.length; k++) {
				int x = i + visits[k][0];
				int y = j + visits[k][1];

				if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0 && !visited[x][y]) {
					count[x][y] = 1 + currentCount;
					q.add(new int[] { x, y });
					visited[x][y] = true;
				}
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
