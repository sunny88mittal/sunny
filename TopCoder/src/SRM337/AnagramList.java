package SRM337;

import java.util.HashMap;
import java.util.Map;

public class AnagramList {

	String finalString = "";

	int length;

	Map<Character, Integer> charCountMap = new HashMap<Character, Integer>();

	public String getAnagram(String s, int i) {
		int[] charCount = new int[26];
		length = s.length();
		for (char c : s.toCharArray()) {
			charCount[c - 'a']++;
			charCountMap.put(c, charCount[c-'a']);
		}

		makeAnagaram(charCount, i);

		return finalString;
	}

	private void makeAnagaram(int[] charCount, int i) {
		if (i == 0) {
			for (int j = 0; j < 26; j++) {
				for (int k = 0; k < charCount[i]; k++) {
					finalString += 'a' + j;
				}
			}
		}

		int sum = 0;

	}
}
