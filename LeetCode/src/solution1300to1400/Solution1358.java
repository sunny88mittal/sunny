package solution1300to1400;

import java.util.HashSet;
import java.util.Set;

public class Solution1358 {
	public int numberOfSubstrings(String s) {
		Set<Character> charSet = new HashSet<Character>();
		int[] latestPlace = new int[3];
		int count = 0;

		for (int i = 0; i < s.length(); i++) {
			charSet.add(s.charAt(i));
			latestPlace[s.charAt(i) - 'a'] = i;
			if (charSet.size() == 3) {
				int startedAt = Math.min(latestPlace[0], Math.min(latestPlace[1], latestPlace[2]));
				count += startedAt + 1;
			}
		}

		return count;
	}
}
