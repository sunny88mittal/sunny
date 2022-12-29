public class WordSearch {

	public boolean exist(char[][] board, String word) {
		int m = board.length;
		int n = board[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == word.charAt(0)) {
					boolean used[][] = new boolean[m][n];
					used[i][j] = true;
					if (checkIfWordThere(board, used, m, n, i, j, word, 1)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private boolean checkIfWordThere(char[][] board, boolean used[][], int m, int n, int i, int j, String word, int k) {
		if (k == word.length()) {
			return true;
		}

		boolean returnValue;

		// Right
		if (j + 1 < n && !used[i][j + 1] && board[i][j + 1] == word.charAt(k)) {
			used[i][j + 1] = true;
			returnValue = checkIfWordThere(board, used, m, n, i, j + 1, word, k + 1);
		} else {
			returnValue = false;
		}

		// Down
		if (i + 1 < m && !used[i + 1][j] && board[i + 1][j] == word.charAt(k)) {
			used[i + 1][j] = true;
			returnValue |= checkIfWordThere(board, used, m, n, i + 1, j, word, k + 1);
		} else {
			returnValue |= false;
		}

		// Left
		if (j - 1 >= 0 && !used[i][j - 1] && board[i][j - 1] == word.charAt(k)) {
			used[i][j - 1] = true;
			returnValue |= checkIfWordThere(board, used, m, n, i, j - 1, word, k + 1);
		} else {
			returnValue |= false;
		}

		// Up
		if (i - 1 >= 0 && !used[i - 1][j] && board[i - 1][j] == word.charAt(k)) {
			used[i - 1][j] = true;
			returnValue |= checkIfWordThere(board, used, m, n, i - 1, j, word, k + 1);
		} else {
			returnValue |= false;
		}

		used[i][j] = false;

		return returnValue;
	}

	public static void main(String args[]) {
		WordSearch obj = new WordSearch();
		obj.exist(new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'E', 'S' }, { 'A', 'D', 'E', 'E' } },
				"ABCESEEEFS");
	}
}
