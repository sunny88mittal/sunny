import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords {
	class WordCount {
		String word;
		Integer count = 0;
	}

	public List<String> topKFrequent(String[] words, int k) {
		List<String> freqWords = new ArrayList<String>();
		Map<String, Integer> wordCount = new HashMap<String, Integer>();
		PriorityQueue<WordCount> queue = new PriorityQueue<>(
				(word1, word2) -> word2.count.compareTo(word1.count) != 0 ? word2.count.compareTo(word1.count)
						: word1.word.compareTo(word2.word));
		for (String word : words) {
			wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
		}

		for (String key : wordCount.keySet()) {
			int value = wordCount.get(key);
			WordCount obj = new WordCount();
			obj.word = key;
			obj.count = value;
			queue.add(obj);
		}

		while (k > 0) {
			freqWords.add(queue.poll().word);
			--k;
		}

		return freqWords;
	}

	public static void main(String args[]) {
		String words[] = new String[] { "i", "love", "leetcode", "i", "love", "coding" };
		TopKFrequentWords obj = new TopKFrequentWords();
		obj.topKFrequent(words, 2);
	}
}
