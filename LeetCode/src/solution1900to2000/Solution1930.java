package solution1900to2000;

import java.util.HashSet;
import java.util.Set;

public class Solution1930 {
	public int countPalindromicSubsequence(String s) {
		int[] leftCounts = new int[26];
		int[] rightCounts = new int[26];
		Set<String> palindromes = new HashSet<>();
		int n = s.length();
		for (int i = 0; i < n; i++) {
			++rightCounts[s.charAt(i) - 'a'];
		}

		for (int i = 0; i < n; i++) {
			--rightCounts[s.charAt(i) - 'a'];
			if (i >= 1 && i <= n - 2) {
				for (int j = 0; j < 26; j++) {
					if (leftCounts[j] > 0 && rightCounts[j] > 0) {
						String palindrome = ('a' + j) + "" + s.charAt(i) + "" + ('a' + j);
						palindromes.add(palindrome);
					}
				}
			}
			++leftCounts[s.charAt(i) - 'a'];
		}

		return palindromes.size();
	}
}
