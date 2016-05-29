package jda;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JungleRun {

	private static int MIN_PATH = Integer.MAX_VALUE;

	private static boolean[][] VISITED_MATRIX;

	private static char[][] pathArray;

	private static char SOURCE = 'S';

	private static char DEST = 'E';

	private static char PATH = 'P';

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line);
		pathArray = new char[N][N];
		for (int i = 0; i < N; i++) {
			String tokens[] = br.readLine().split(" ");
			for (int j=0; j<N; j++) {
				pathArray[i][j] = tokens[j].charAt(0);
			}
		}

		// Find the source and destination
		int sx = 0, sy = 0, dx = 0, dy = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pathArray[i][j] == SOURCE) {
					sx = i;
					sy = j;
				} else if (pathArray[i][j] == DEST) {
					dx = i;
					dy = j;
				}
			}
		}

		// Initialize the visted matrix
		VISITED_MATRIX = new boolean[N][N];

		// Get the path
		visitPaths(sx, sy, dx, dy, 0);

		System.out.println(MIN_PATH);
	}

	private static void visitPaths(int sx, int sy, int dx, int dy, int pathLength) {
		
		if (pathLength >= MIN_PATH) { //No use of searching if the pathLenght is going to be greater than current min
			return;
		}
		
		// East
		if ((sx + 1) < pathArray.length) {
			if (pathArray[sx + 1][sy] == DEST) {
				if (pathLength + 1 < MIN_PATH) {
					MIN_PATH = pathLength + 1;
				}
				return;
			}

			if (pathArray[sx + 1][sy] == PATH && !VISITED_MATRIX[sx + 1][sy]) {
				VISITED_MATRIX[sx + 1][sy] = true;
				visitPaths(sx + 1, sy, dx, dy, pathLength + 1);
				VISITED_MATRIX[sx + 1][sy] = false;
			}
		}
		
		// West
		if ((sx - 1) >= 0) {
			if (pathArray[sx - 1][sy] == DEST) {
				if (pathLength + 1 < MIN_PATH) {
					MIN_PATH = pathLength + 1;
				}
				return;
			}

			if (pathArray[sx - 1][sy] == PATH && !VISITED_MATRIX[sx - 1][sy]) {
				VISITED_MATRIX[sx - 1][sy] = true;
				visitPaths(sx - 1, sy, dx, dy, pathLength + 1);
				VISITED_MATRIX[sx - 1][sy] = false;
			}
		}
		
		// North
		if ((sy - 1) >= 0) {
			if (pathArray[sx][sy-1] == DEST) {
				if (pathLength + 1 < MIN_PATH) {
					MIN_PATH = pathLength + 1;
				}
				return;
			}

			if (pathArray[sx][sy-1] == PATH && !VISITED_MATRIX[sx][sy-1]) {
				VISITED_MATRIX[sx][sy-1] = true;
				visitPaths(sx, sy-1, dx, dy, pathLength + 1);
				VISITED_MATRIX[sx][sy-1] = false;
			}
		}


		// South
		if ((sy + 1) < pathArray.length) {
			if (pathArray[sx][sy+1] == DEST) {
				if (pathLength + 1 < MIN_PATH) {
					MIN_PATH = pathLength + 1;
				}
				return;
			}

			if (pathArray[sx][sy+1] == PATH && !VISITED_MATRIX[sx][sy+1]) {
				VISITED_MATRIX[sx][sy+1] = true;
				visitPaths(sx, sy+1, dx, dy, pathLength + 1);
				VISITED_MATRIX[sx][sy+1] = false;
			}
		}
	}
}
