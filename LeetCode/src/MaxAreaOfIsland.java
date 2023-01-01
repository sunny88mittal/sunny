import java.util.LinkedList;
import java.util.Queue;

public class MaxAreaOfIsland {

	class Pair {
		int i;
		int j;

		Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public int maxAreaOfIsland(int[][] grid) {
		int maxAreaOfIsland = 0;
		int rows = grid.length;
		int cols = grid[0].length;
		boolean visited[][] = new boolean[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j] == 1 && !visited[i][j]) {
					int area = doBFS(grid, visited, i, j);
					maxAreaOfIsland = maxAreaOfIsland < area ? area : maxAreaOfIsland;
				}
			}
		}

		return maxAreaOfIsland;
	}

	public int doBFS(int[][] grid, boolean[][] visited, int i, int j) {
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(i, j));

		int area = 0;
		while (!queue.isEmpty()) {
			Pair pair = queue.poll();
			int row = pair.i;
			int col = pair.j;
			visited[row][col] = true;
			++area;

			if (col + 1 < grid[0].length && grid[row][col + 1] == 1 && !visited[row][col + 1]) {
				queue.add(new Pair(row, col + 1));
				visited[row][col + 1] = true;
			}

			if (col - 1 >= 0 && grid[row][col - 1] == 1 && !visited[row][col - 1]) {
				queue.add(new Pair(row, col - 1));
				visited[row][col - 1] = true;
			}

			if (row + 1 < grid.length && grid[row + 1][col] == 1 && !visited[row + 1][col]) {
				queue.add(new Pair(row + 1, col));
				visited[row + 1][col] = true;
			}

			if (row - 1 >= 0 && grid[row - 1][col] == 1 && !visited[row - 1][col]) {
				queue.add(new Pair(row - 1, col));
				visited[row - 1][col] = true;
			}
		}

		return area;
	}
}
