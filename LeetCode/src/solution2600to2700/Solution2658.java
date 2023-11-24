package solution2600to2700;

import java.util.LinkedList;
import java.util.Queue;

public class Solution2658 {
	public int findMaxFish(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		boolean visited[][] = new boolean[m][n];
		Queue<String> queue = new LinkedList<>();

		int maxFish = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && grid[i][j] != 0) {
					queue.add(i + "," + j);
					visited[i][j] = true;
					int currentSum = 0;
					while (!queue.isEmpty()) {
						String[] elements = queue.remove().split(",");
						int r = Integer.parseInt(elements[0]);
						int c = Integer.parseInt(elements[1]);
						currentSum += grid[r][c];

						if (r + 1 < m && !visited[r + 1][c] && grid[r + 1][c] != 0) {
							queue.add((r + 1) + "," + c);
							visited[r + 1][c] = true;
						}

						if (c + 1 < n && !visited[r][c + 1] && grid[r][c + 1] != 0) {
							queue.add(r + "," + (c + 1));
							visited[r][c + 1] = true;
						}

						if (r - 1 >= 0 && !visited[r - 1][c] && grid[r - 1][c] != 0) {
							queue.add((r - 1) + "," + c);
							visited[r - 1][c] = true;
						}

						if (c - 1 >= 0 && !visited[r][c - 1] && grid[r][c - 1] != 0) {
							queue.add(r + "," + (c - 1));
							visited[r][c - 1] = true;
						}
					}

					if (currentSum > maxFish) {
						maxFish = currentSum;
					}
				}
			}
		}

		return maxFish;
	}

	public static void main(String args[]) {
		Solution2658 obj = new Solution2658();
		int s[][] = new int[][] { { 8, 6 }, { 2, 6 } };
		obj.findMaxFish(s);
	}
}
