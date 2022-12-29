import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortCharactersByFrequency {

	class CharacterCountPair {
		char c;
		Integer count = 0;
	}

	public String frequencySort(String s) {
		char[] chars = s.toCharArray();
		Map<Character, Integer> charCountMap = new HashMap<>();
		for (char c : chars) {
			charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
		}

		PriorityQueue<CharacterCountPair> queue = new PriorityQueue<>((c1, c2) -> c2.count.compareTo(c1.count));
		for (Character key : charCountMap.keySet()) {
			CharacterCountPair obj = new CharacterCountPair();
			obj.c = key;
			obj.count = charCountMap.get(key);
			queue.add(obj);
		}

		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {
			CharacterCountPair pair = queue.poll();
			for (int i = 0; i < pair.count; i++) {
				sb.append(pair.c);
			}
		}

		return sb.toString();
	}
}
