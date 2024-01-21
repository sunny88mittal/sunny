package solution3000to3100;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution3016 {

	public int minimumPushes(String word) {
		int count = 0;
		Map<Character, Integer> countMap = new HashMap<>();
		for (char ch : word.toCharArray()) {
			int currentCount = 0;
			if (countMap.containsKey(ch)) {
				currentCount = countMap.get(ch);
			}
			++currentCount;
			countMap.put(ch, currentCount);
		}

		PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
		for (int value : countMap.values()) {
			queue.add(value);
		}

		int multiple = 1;
		int elementsAdded = 0;
		while (!queue.isEmpty()) {
			int element = queue.remove();
			count += element * multiple;
			++elementsAdded;
			if (elementsAdded % 8 == 0) {
				++multiple;
			}
		}

		return count;
	}
}
