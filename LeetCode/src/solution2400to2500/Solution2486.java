package solution2400to2500;

public class Solution2486 {
	public int appendCharacters(String s, String t) {
		int i = 0, j = 0;

		while (i < s.length() && j < t.length()) {
			if (s.charAt(i) == t.charAt(j)) {
				++j;
			}
			++i;
		}

		return t.length() - j;
	}
}
