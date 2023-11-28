package solution2300to2400;

public class Solution2381 {
	public String shiftingLetters(String s, int[][] shifts) {
		int n = s.length();
		int change[] = new int[n];

		// Calculate the change for each character
		for (int i = 0; i < shifts.length; i++) {
			int direction = shifts[i][2];
			int start = shifts[i][0];
			int end = shifts[i][1];

			if (direction == 0) {
				direction = -1;
			}
			for (int j = start; j <= end; j++) {
				change[j] += direction;
			}
		}

		// Apply the change
		char[] res = new char[n];
		for (int i = 0; i < n; i++) {
			change[i] %= 26;
			if (change[i] < 0) {
				change[i] += 26;
			}

			res[i] = (char) ('a' + (s.charAt(i) - 'a' + change[i]) % 26);
		}

		return new String(res);
	}
}
