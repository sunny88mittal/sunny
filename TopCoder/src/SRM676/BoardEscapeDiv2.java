package SRM676;

public class BoardEscapeDiv2 {

	private int[][][] dp = new int[50][50][101];

	private String s[];

	public String findWinner(String[] s, int k) {
		this.s = s;
		int ti = -1, tj = -1;
		// Find the token's current position
		for (int i = 0; i < s.length; i++) {
			char ch[] = s[i].toCharArray();
			int len = ch.length;
			for (int m = 0; m < len; m++) {
				if (ch[m] == 'T') {
					ti = i;
					tj = m;
				}
			}
		}

		int value = findWinnerP(k, ti, tj, 1);
		return value == 1 ? "Alice" : "Bob";
	}

	private int findWinnerP(int k, int i, int j, int turn) {
		int res = dp[i][j][k];
		if (res == 0) {
			//Check if the user who has to take turn is blocked
			if (k == 0 || s[i].charAt(j) == 'E') {
				res = turn == 1 ? 2 : 1;
			} else {//All four cases (left, right, up, down)
				int leftC = j - 1;
				if (leftC >= 0 && s[i].charAt(leftC) != '#') {
					res = findWinnerP(k - 1, i, leftC, turn == 1 ? 2 : 1);
				}
				
				int rightC = j+1;
				if (res != turn && rightC < s[0].length() && s[i].charAt(rightC) != '#') {
					res = findWinnerP(k - 1, i, rightC, turn == 1 ? 2 : 1);
				}
				
				int upC = i-1;
				if (res != turn && upC >=0 && s[upC].charAt(j) != '#') {
					res = findWinnerP(k - 1, upC, j, turn == 1 ? 2 : 1);
				}
				
				int downC = i+1;
				if (res != turn && downC < s.length && s[downC].charAt(j) != '#') {
					res = findWinnerP(k - 1, downC, j, turn == 1 ? 2 : 1);
				}
			}
			dp[i][j][k] = res;
		}
		return res;
	}
}
