package solution1200to1300;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Solution1249 {
	public String minRemoveToMakeValid(String s) {
		Stack<Integer> st = new Stack<Integer>();
		Set<Integer> removed = new HashSet<>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				st.add(i);
			} else if (s.charAt(i) == ')') {
				if (!st.isEmpty() && s.charAt(st.peek()) == '(') {
					st.remove(0);
				} else {
					removed.add(i);
				}
			}
		}

		removed.addAll(st);

		char finalString[] = new char[s.length() - removed.size()];
		int j = 0;
		for (int i = 0; i < s.length(); i++) {
			if (!removed.contains(i)) {
				finalString[j] = s.charAt(i);
				++j;
			}
		}

		return new String(finalString);
	}
}
