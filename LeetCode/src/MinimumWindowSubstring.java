import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

	public String minWindow(String s, String t) {
		int start = 0;
		String minString = "";
		int charsMatched = 0;
		Map<Character, Integer> sourceCharCountMap = new HashMap<Character, Integer>();
		Map<Character, Integer> patternCharCountMap = getCharCountMap(t);
		for (int i = 0; i < s.length(); i++) {
			charsMatched = addCharacter(s.charAt(i), sourceCharCountMap, patternCharCountMap, charsMatched);
			if (charsMatched == t.length()) {
				while (charsMatched == t.length()) {
					String newMinString = s.substring(start, i + 1);
					if (minString.equals("") || newMinString.length() < minString.length()) {
						minString = newMinString;
					}
					charsMatched = removeCharacter(s.charAt(start), sourceCharCountMap, patternCharCountMap,
							charsMatched);
					++start;
				}
			}
		}

		return minString;
	}

	private int addCharacter(char ch, Map<Character, Integer> sourceCharCountMap,
			Map<Character, Integer> patternCharCountMap, int matchCount) {
		sourceCharCountMap.put(ch, sourceCharCountMap.getOrDefault(ch, 0) + 1);
		int patternCharCount = patternCharCountMap.getOrDefault(ch, 0);
		int sourceCharCount = sourceCharCountMap.get(ch);
		if (patternCharCount >= sourceCharCount) {
			++matchCount;
		}
		return matchCount;
	}

	private int removeCharacter(char ch, Map<Character, Integer> sourceCharCountMap,
			Map<Character, Integer> patternCharCountMap, int matchCount) {
		sourceCharCountMap.put(ch, sourceCharCountMap.get(ch) - 1);
		int patternCharCount = patternCharCountMap.getOrDefault(ch, 0);
		int sourceCharCount = sourceCharCountMap.get(ch);
		if (sourceCharCount < patternCharCount) {
			--matchCount;
		}
		return matchCount;
	}

	private Map<Character, Integer> getCharCountMap(String s) {
		Map<Character, Integer> charCountMap = new HashMap<Character, Integer>();
		for (char ch : s.toCharArray()) {
			charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
		}
		return charCountMap;
	}

	public static void main(String args[]) {
		MinimumWindowSubstring obj = new MinimumWindowSubstring();
		obj.minWindow("a", "a");
	}
}
