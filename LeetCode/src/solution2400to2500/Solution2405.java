package solution2400to2500;

import java.util.HashSet;
import java.util.Set;

public class Solution2405 {
	public int partitionString(String s) {
		int count = 1;
		Set<Character> set = new HashSet<>();
		for (char ch : s.toCharArray()) {
			if (set.contains(ch)) {
				set = new HashSet<>();
				++count;
			}
			set.add(ch);
		}

		return count;
	}
}
