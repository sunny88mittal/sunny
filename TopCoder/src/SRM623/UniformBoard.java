package SRM623;

public class UniformBoard {

	private int[][] dotMatrix;

	private int[][] appleMatrix;

	private int[][] pearMatrix;

	public int getBoard(String[] board, int K) {
		int size = board.length;

		dotMatrix = new int[size + 1][size + 1];
		appleMatrix = new int[size + 1][size + 1];
		pearMatrix = new int[size + 1][size + 1];

		char chars[] = new char[] { '.', 'A', 'P' };

		// For each character calculate the no of characters for position (i,j)
		for (char ch : chars) {
			int[][] toFill = null;
			switch (ch) {
			case '.':
				toFill = dotMatrix;
				break;
			case 'A':
				toFill = appleMatrix;
				break;
			case 'P':
				toFill = pearMatrix;
				break;
			}

			for (int i = 0; i < size; i++) {
				String str = board[i];
				char[] characters = str.toCharArray();
				for (int j = 0; j < size; j++) {
					int value = (characters[j] == ch) ? 1 : 0;
					toFill[i + 1][j + 1] = value + toFill[i][j + 1]
							+ toFill[i + 1][j] - toFill[i][j];
				}
			}
		}

		int totalA = appleMatrix[size][size];
		int totalDot = dotMatrix[size][size];

		int max = 0;

		for (int x0 = 0; x0 < size; x0++) {
			for (int y0 = 0; y0 < size; y0++) {
				for (int x1 = x0 + 1; x1 <= size; x1++) {
					for (int y1 = y0 + 1; y1 <= size; y1++) {
						int area = (y1 - y0) * (x1 - x0);
						int dots = getCount(dotMatrix, x0, y0, x1, y1);
						int pears = getCount(pearMatrix, x0, y0, x1, y1);

						if ((totalA >= area) && (pears == 0 || totalDot > 0)) {
							if (dots + 2 * pears <= K) {
								if (max < area) {
									max = area;
								}
							}
						}
					}
				}
			}
		}

		return max;
	}

	private int getCount(int[][] array, int x0, int y0, int x1, int y1) {
		return array[x1][y1] - array[x0][y1] - array[x1][y0] + array[x0][y0];
	}
}
