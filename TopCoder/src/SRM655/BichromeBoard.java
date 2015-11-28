package SRM655;

//Better solution at http://apps.topcoder.com/wiki/display/tc/SRM+655
//Just check for both the cases using simple equation
public class BichromeBoard {

	public String ableToDraw(String[] board) {
		int n = board.length;
		int m = board[0].length();
		char ch[][] = new char[n][m];
		for (int i = 0; i < n; i++) {
			ch[i] = board[i].toCharArray();
		}

		// Get a char location
		int pi = -1, pj = -1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (ch[i][j] != '?') {
					pi = i;
					pj = j;
					break;
				}
			}
			if (pi != -1 && pj != -1) {
				break;
			}
		}
		// Empty Board
		if (pi == -1 && pj == -1) {
			return "Possible";
		}

		// Fill the board

		// Fill one column
		int i = pi + 1;
		while (i < n) {
			if (ch[i][pj] == '?') {
				ch[i][pj] = ch[i - 1][pj] == 'W' ? 'B' : 'W';
			}
			i++;
		}
		i = pi - 1;
		while (i >= 0) {
			if (ch[i][pj] == '?') {
				ch[i][pj] = ch[i + 1][pj] == 'W' ? 'B' : 'W';
			}
			i--;
		}

		// Fill all rows
		for (i = 0; i < n; i++) {
			pj = 0;
			for (int k = 0; k < m; k++) {
				if (ch[i][k] != '?') {
					pj = k;
					break;
				}
			}

			int j = pj + 1;
			while (j < m) {
				if (ch[i][j] == '?') {
					ch[i][j] = ch[i][j - 1] == 'W' ? 'B' : 'W';
				}
				j++;
			}
			j = pj - 1;
			while (j >= 0) {
				if (ch[i][j] == '?') {
					ch[i][j] = ch[i][j + 1] == 'W' ? 'B' : 'W';
				}
				j--;
			}
		}

		// Verify the board

		// check rows
		for (i = 0; i < n; i++) {
			for (int j = 0; j < m - 1; j++) {
				if (ch[i][j] == ch[i][j + 1]) {
					return "Impossible";
				}
			}
		}

		// Check Columns
		for (int j = 0; j < m; j++) {
			for (i = 0; i < n-1; i++) {
				if (ch[i][j] == ch[i+1][j]) {
					return "Impossible";
				}
			}
		}

		return "Possible";
	}
}
