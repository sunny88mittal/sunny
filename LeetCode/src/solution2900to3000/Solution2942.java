package solution2900to3000;

import java.util.ArrayList;
import java.util.List;

public class Solution2942 {
	public List<Integer> findWordsContaining(String[] words, char x) {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			if (words[i].contains(x + "")) {
				result.add(i);
			}
		}
		return result;
	}
}
