package SRM689;

import java.util.HashSet;
import java.util.Set;

public class NonDeterministicSubstring {

	public long ways(String A, String B) {
		int al = A.length();
		int bl = B.length();
		Set<String> countSet = new HashSet<String>();

		int count = al - bl;
		B = B.replace('?', '.');
		for (int i = 0; i <= count; i++) {
			String tmp = A.substring(i, i + bl);
			if (tmp.matches(B)) {
				countSet.add(tmp);
			}
		}

		return (long)countSet.size();
	}
}
