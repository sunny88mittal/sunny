package solution100to200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution187 {
	public List<String> findRepeatedDnaSequences(String s) {
		List<String> ans = new ArrayList<>();
		if (s.length() < 10) {
			return ans;
		}

		Map<String, Integer> stringCount = new HashMap<>();

		for (int i = 0; i <= s.length() - 10; i++) {
			String startingString = s.substring(i, i + 10);
			Integer currentCouunt = stringCount.get(startingString);
			if (currentCouunt == null) {
				currentCouunt = 0;
			}
			stringCount.put(startingString, ++currentCouunt);
		}

		for (String key : stringCount.keySet()) {
			if (stringCount.get(key) > 1) {
				ans.add(key);
			}
		}

		return ans;
	}
}
