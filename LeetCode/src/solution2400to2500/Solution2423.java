package solution2400to2500;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution2423 {
	public boolean equalFrequency(String word) {
		int count[] = new int[26];
		for (char ch : word.toCharArray()) {
			++count[ch - 'a'];
		}

		List<Integer> nonZeroCounts = new ArrayList<>();
		for (int c : count) {
			if (c != 0) {
				nonZeroCounts.add(c);
			}
		}

		Collections.sort(nonZeroCounts);

		for (int i = 0; i < nonZeroCounts.size(); i++) {
			nonZeroCounts.set(i, nonZeroCounts.get(i) - 1);
			if (validateList(nonZeroCounts)) {
				return true;
			}
			nonZeroCounts.set(i, nonZeroCounts.get(i) + 1);
		}

		return false;
	}

	private static boolean validateList(List<Integer> counts) {
		for (int i = 0; i < counts.size() - 1; i++) {
			if (counts.get(i) == 0) {
				continue;
			}
			if (counts.get(i) != counts.get(i + 1)) {
				return false;
			}
		}

		return true;
	}

	public static void main(String args[]) {
		Solution2423 obj = new Solution2423();
		obj.equalFrequency("abcc");
	}
}
