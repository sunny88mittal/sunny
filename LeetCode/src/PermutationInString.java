
public class PermutationInString {
	public boolean checkInclusion(String s1, String s2) {
		int s1CharCount[] = new int[26];
		int s2CharCount[] = new int[26];

		for (char c : s1.toCharArray()) {
			++s1CharCount[c - 'a'];
		}

		int j = 0;
		for (int i = 0; i < s2.length(); i++) {
			++s2CharCount[s2.charAt(i) - 'a'];
			if (permMatch(s1CharCount, s2CharCount)) {
				return true;
			}

			while ((i - j + 1) >= s1.length()) {
				--s2CharCount[s2.charAt(j) - 'a'];
				++j;
			}
		}

		return false;
	}

	private boolean permMatch(int s1CharCount[], int s2CharCount[]) {
		for (int i = 0; i < s1CharCount.length; i++) {
			if (s1CharCount[i] != s2CharCount[i]) {
				return false;
			}
		}

		return true;
	}
}
