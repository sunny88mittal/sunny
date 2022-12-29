
public class LongestPalindromicSubstring {

	public String longestPalindrome(String s) {
		int length = s.length();
		String lps = "";
		int lpsCounter[][] = new int[length][length];

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (i == j) {
					lpsCounter[i][j] = 1;
				}
			}
		}

		for (int i = 0; i < length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (s.charAt(i) == s.charAt(j)) {
					if (j + 1 <= i - 1) {
						if (lpsCounter[i - 1][j + 1] > 0) {
							lpsCounter[i][j] = 2 + lpsCounter[i - 1][j + 1];
						} else {
							lpsCounter[i][j] = 0;
						}
					} else {
						lpsCounter[i][j] = 2;
					}
				}
			}
		}

		for (int i = 0; i < length; i++) {
			for (int j = 0; j <= i; j++) {
				if (lps.length() < lpsCounter[i][j]) {
					lps = s.substring(j, i + 1);
				}
			}
		}

		return lps;
	}

	public static void main(String args[]) {
		LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
		lps.longestPalindrome("a");
	}
}
