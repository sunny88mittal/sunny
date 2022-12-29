import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {

	public int totalFruit(int[] fruits) {
		int max = 0;
		Map<Integer, Integer> fruitCountMap = new HashMap<Integer, Integer>();
		int start = 0;
		for (int i = 0; i < fruits.length; i++) {
			Integer existingCount = fruitCountMap.get(fruits[i]);
			if (existingCount == null) {
				existingCount = 0;
			}
			++existingCount;
			fruitCountMap.put(fruits[i], existingCount);

			while (fruitCountMap.size() > 2) {
				Integer count = fruitCountMap.get(fruits[start]);
				--count;
				if (count == 0) {
					fruitCountMap.remove(fruits[start]);
				} else {
					fruitCountMap.put(fruits[start], count);
				}
				++start;
			}

			max = Math.max(max, i + 1 - start);
		}
		return max;
	}
}
