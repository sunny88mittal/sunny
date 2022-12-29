import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenation {

	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> starts = new ArrayList<Integer>();
		Map<String, Integer> wordsSet = new HashMap<String, Integer>();
		for (String word : words) {
			int count = 1;
			if (wordsSet.containsKey(word)) {
				count += wordsSet.get(word);
			}
			wordsSet.put(word, count);
		}

		int wordLength = words[0].length();
		int start = 0;
		int maxLengthToCheck = words.length * wordLength;
		while (start < (s.length() - maxLengthToCheck)) {
			Map<String, Integer> wordsSetCopy = new HashMap<String, Integer>(wordsSet);
			wordsSetCopy.putAll(wordsSet);
			if (containsWords(s.substring(start, start + maxLengthToCheck), wordsSetCopy, wordLength)) {
				starts.add(start);
			}
			start += wordLength;
		}

		return starts;
	}

	private boolean containsWords(String s, Map<String, Integer> wordsSet, int wordLength) {
		if (wordsSet.isEmpty()) {
			return true;
		}

		if (s.length() < wordLength) {
			return false;
		}

		String word = s.substring(0, wordLength);
		Integer count = wordsSet.remove(word);
		if (count == null) {
			return false;
		}

		count -= 1;
		if (count > 0) {
			wordsSet.put(word, count);
		}

		return containsWords(s.substring(wordLength), wordsSet, wordLength);
	}
}
