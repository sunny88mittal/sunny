package solution100to200;

import java.util.LinkedList;
import java.util.Queue;

public class Solution130 {
	public void solve(char[][] board) {
		int m = board.length;
		int n = board[0].length;

		boolean visited[][] = new boolean[m][n];
		int[][] visits = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
					if (board[i][j] == 'O') {
						Queue<int[]> queue = new LinkedList<>();
						queue.add(new int[] { i, j });
						while (!queue.isEmpty()) {
							int[] cell = queue.remove();
							int p = cell[0];
							int q = cell[1];
							board[p][q] = '.';
							visited[p][q] = true;
							for (int[] visit : visits) {
								int x = p + visit[0];
								int y = q + visit[1];
								if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O' && !visited[x][y]) {
									queue.add(new int[] { x, y });
								}
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
				if (board[i][j] == '.') {
					board[i][j] = 'O';
				}
			}
		}
	}
}
