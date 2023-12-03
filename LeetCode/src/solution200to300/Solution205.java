package solution200to300;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution205 {
	public boolean isIsomorphic(String s, String t) {
		Map<Character, Character> replacement = new HashMap<>();
		Set<Character> used = new HashSet<>();
		for (int i = 0; i < s.length(); i++) {
			if (replacement.get(s.charAt(i)) == null && !used.contains(t.charAt(i))) {
				replacement.put(s.charAt(i), t.charAt(i));
				used.add(t.charAt(i));
			}

			if (replacement.get(s.charAt(i)) == null || replacement.get(s.charAt(i)) != t.charAt(i)) {
				return false;
			}
		}

		return true;
	}
}
