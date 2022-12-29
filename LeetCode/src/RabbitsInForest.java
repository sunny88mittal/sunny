import java.util.HashMap;

public class RabbitsInForest {

	public int numRabbits(int[] answers) {
		HashMap<Integer, Double> map = new HashMap();
		for (int num : answers) {
			map.put(num, map.getOrDefault(num, 0.0) + 1);
		}
		int res = 0;
		for (int key : map.keySet()) {
			res += (key + 1) * Math.ceil(map.get(key) / (key + 1));
		}
		return res;
	}
}
