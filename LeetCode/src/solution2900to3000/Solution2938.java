package solution2900to3000;

public class Solution2938 {
	public long minimumSteps(String s) {
		long count = 0;
		int toPutZeroAt = -1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1' && toPutZeroAt == -1) {
				toPutZeroAt = i;
				continue;
			}

			if (s.charAt(i) == '0' && toPutZeroAt != -1) {
				count += (long)(i - toPutZeroAt);
				++toPutZeroAt;
			}
		}
		return count;
	}
}