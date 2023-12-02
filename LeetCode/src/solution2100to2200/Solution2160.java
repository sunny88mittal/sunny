package solution2100to2200;

import java.util.Arrays;

public class Solution2160 {
	public int minimumSum(int num) {
		int digits[] = new int[4];
		int i = 0;
		while (num > 0) {
			digits[i] = num % 10;
			num /= 10;
			++i;
		}

		Arrays.sort(digits);

		return digits[2] + digits[3] + 10 * digits[0] + 10 * digits[1];
	}
}
