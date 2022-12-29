import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

	public int lengthOfLongestSubstring(String s) {
		int longestSusbtring = 0;
		char[] chars = s.toCharArray();
		int start = 0;
		Map<Character, Integer> charPos = new HashMap<Character, Integer>();

		int i = 0;
		for (i = 0; i < chars.length; i++) {
			char ch = chars[i];
			if (!charPos.containsKey(ch)) {
				charPos.put(ch, i);
			} else {
				int pos = charPos.get(ch);
				if (pos >= start) {
					int newSusbtringLength = i - start;
					start = pos + 1;
					if (newSusbtringLength > longestSusbtring) {
						longestSusbtring = newSusbtringLength;
					}
				}
				charPos.put(ch, i);
			}
		}

		if ((i - start) > longestSusbtring) {
			longestSusbtring = i - start;
		}

		return longestSusbtring;
	}

	public static void main(String args[]) {
		LongestSubstringWithoutRepeatingCharacters obj = new LongestSubstringWithoutRepeatingCharacters();
		obj.lengthOfLongestSubstring("aabaab!bb");
	}
}
