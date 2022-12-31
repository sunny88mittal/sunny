import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {

	class Pair {
		int i;
		int j;

		Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public int numIslands(char[][] grid) {
		int noOfIslands = 0;
		int rows = grid.length;
		int cols = grid[0].length;
		boolean visited[][] = new boolean[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j] == '1' && !visited[i][j]) {
					++noOfIslands;
					doBFS(grid, visited, i, j);
				}
			}
		}

		return noOfIslands;
	}

	public void doBFS(char[][] grid, boolean[][] visited, int i, int j) {
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(i, j));

		while (!queue.isEmpty()) {
			Pair pair = queue.poll();
			int row = pair.i;
			int col = pair.j;
			visited[row][col] = true;

			if (col + 1 < grid[0].length && grid[row][col + 1] == '1' && !visited[row][col + 1]) {
				queue.add(new Pair(row, col + 1));
				visited[row][col + 1] = true;
			}

			if (col - 1 >= 0 && grid[row][col - 1] == '1' && !visited[row][col - 1]) {
				queue.add(new Pair(row, col - 1));
				visited[row][col - 1] = true;
			}

			if (row + 1 < grid.length && grid[row + 1][col] == '1' && !visited[row + 1][col]) {
				queue.add(new Pair(row + 1, col));
				visited[row + 1][col] = true;
			}

			if (row - 1 >= 0 && grid[row - 1][col] == '1' && !visited[row - 1][col]) {
				queue.add(new Pair(row - 1, col));
				visited[row - 1][col] = true;
			}
		}
	}
}
