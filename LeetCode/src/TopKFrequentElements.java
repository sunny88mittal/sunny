import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {

	class Pair {
		int element;
		Integer count;
	}

	public int[] topKFrequent(int[] nums, int k) {
		int[] result = new int[k];
		Map<Integer, Integer> elementCount = new HashMap<>();
		for (int num : nums) {
			elementCount.put(num, elementCount.getOrDefault(num, 0) + 1);
		}

		PriorityQueue<Pair> queue = new PriorityQueue<>((p1, p2) -> p2.count.compareTo(p1.count));
		for (Integer key : elementCount.keySet()) {
			int value = elementCount.get(key);
			Pair pair = new Pair();
			pair.element = key;
			pair.count = value;
			queue.add(pair);
		}

		while (k > 0) {
			--k;
			result[k] = queue.poll().element;
		}

		return result;
	}
}
