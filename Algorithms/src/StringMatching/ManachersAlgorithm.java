package StringMatching;

public class ManachersAlgorithm {

	private static String getNewString(String s) {
		StringBuilder sb = new StringBuilder();
		sb.append("@");
		for (int i = 0; i < s.length(); i++) {
			sb.append("#").append(s.charAt(i));
		}
		sb.append("#$");
		return sb.toString();
	}

	private static String longestPlaindrome(String s) {
		String sn = getNewString(s);
		int[] P = new int[sn.length()];
		int r = 0, c = 0;
		for (int i = 1; i < sn.length() - 1; i++) {
			int mirror = c - (i - c);

			if (r > i) {
				P[i] = Math.min(r - i, P[mirror]);
			}

			// expand around current palindrome
			while (sn.charAt(P[i] + i + 1) == sn.charAt(i - 1 - P[i])) {
				P[i]++;
			}

			// Change center if the palindrome has expanded beyond r
			if (i + P[i] > r) {
				c = i;
				r = i + P[i];
			}
		}

		// Find the max length plaindrome
		int maxPalindrome = 0;
		int centerIndex = 0;
		for (int i = 1; i < sn.length() - 1; i++) {
			if (P[i] > maxPalindrome) {
				maxPalindrome = P[i];
				centerIndex = i;
			}
		}

		int start = (centerIndex - 1 - maxPalindrome) / 2;
		return s.substring(start, maxPalindrome + start);
	}

	public static void main(String args[]) {
		System.out.println(longestPlaindrome("banana"));
		System.out.println(longestPlaindrome("kiomaramol"));
	}
}