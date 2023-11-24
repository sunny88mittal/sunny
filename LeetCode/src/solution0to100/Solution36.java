package solution0to100;

public class Solution36 {
	public boolean isValidSudoku(char[][] board) {
		// Validate rows
		for (int i = 0; i < 9; i++) {
			boolean[] seen = new boolean[10];
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					int no = board[i][j] - '0';
					if (seen[no]) {
						return false;
					}
					seen[no] = true;
				}
			}
		}

		// Validate columns
		for (int i = 0; i < 9; i++) {
			boolean[] seen = new boolean[10];
			for (int j = 0; j < 9; j++) {
				if (board[j][i] != '.') {
					int no = board[j][i] - '0';
					if (seen[no]) {
						return false;
					}
					seen[no] = true;
				}
			}
		}

		// Check 3x3 grids
		for (int k = 0; k < 3; k++) {
			int rstart = 3 * k;
			for (int m = 0; m < 3; m++) {
				int cstart = 3 * m;
				boolean[] seen = new boolean[10];
				for (int i = rstart; i < rstart + 3; i++) {
					for (int j = cstart; j < cstart + 3; j++) {
						if (board[i][j] != '.') {
							int no = board[i][j] - '0';
							if (seen[no]) {
								return false;
							}
							seen[no] = true;
						}
					}
				}
			}
		}

		return true;
	}
}
