package solution1600to1700;

public class Solution1662 {
	public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
		String s1 = "";
		String s2 = "";

		for (String w : word1) {
			s1 += w;
		}

		for (String w : word2) {
			s2 += w;
		}

		return s1.equals(s2);
	}
}
