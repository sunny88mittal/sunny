import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInAString {
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> result = new ArrayList<Integer>();
		int pattrenCharCount[] = new int[26];
		int stringCharCount[] = new int[26];

		for (char c : p.toCharArray()) {
			++pattrenCharCount[c - 'a'];
		}

		int j = 0;
		for (int i = 0; i < s.length(); i++) {
			++stringCharCount[s.charAt(i) - 'a'];
			if (anagramMatch(pattrenCharCount, stringCharCount)) {
				result.add(j);
			}

			while ((i - j + 1) >= p.length()) {
				--stringCharCount[s.charAt(j) - 'a'];
				++j;
			}
		}

		return result;
	}

	private boolean anagramMatch(int pattrenCharCount[], int stringCharCount[]) {
		for (int i = 0; i < pattrenCharCount.length; i++) {
			if (pattrenCharCount[i] != stringCharCount[i]) {
				return false;
			}
		}

		return true;
	}
}
