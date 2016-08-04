package com.sunny.matrix;

import java.util.LinkedList;
import java.util.List;

public class ShortestPathInAMaze {

	public static void main(String[] args) {
		int[][] mat = new int[][] 
			{{1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
            {1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
            {1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
            {1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            {1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
            {1, 1, 0, 0, 0, 0, 1, 0, 0, 1 }};
            
		String source = "0,0";
		String dest = "3,4";

		int M = mat.length;
		int N = mat[0].length;

		// Visited matrix
		boolean[][] visited = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			visited[i] = new boolean[M];
		}

		// Count Matrix
		int[][] count = new int[M][N];
		for (int i = 0; i < M; i++) {
			count[i] = new int[M];
		}

		// Valid BFS moves
		int r[] = new int[] { -1, 1, 0, 0 };
		int c[] = new int[] { 0, 0, -1, 1 };

		List<String> queue = new LinkedList<String>();
		queue.add(source);

		while (!queue.isEmpty()) {
			String entry = queue.remove(0);
			String tokens[] = entry.split(",");
			int i = Integer.parseInt(tokens[0]);
			int j = Integer.parseInt(tokens[1]);
			if (entry.equals(dest)) {
				System.out.println(count[i][j]);
				break;
			}

			for (int q = 0; q < 4; q++) {
				int ri = i + r[q];
				int ci = j + c[q];
				if (ri >= 0 && ri < M && ci >= 0 && ci < N) {
					if (mat[ri][ci] == 1 && !visited[ri][ci]) {
                        visited[ri][ci] = true;
                        count[ri][ci] = count[i][j] + 1;
                        queue.add(ri + "," + ci);
					}
				}
			}
		}
	}
}