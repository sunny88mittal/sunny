package solution0to100;

import java.util.ArrayList;
import java.util.List;

public class Solution66 {
	public int[] plusOne(int[] digits) {
		int remainder = 1;
		List<Integer> finalRes = new ArrayList<Integer>();
		for (int i = digits.length - 1; i >= 0; i--) {
			int newDigit = digits[i] + remainder;
			if (newDigit == 10) {
				remainder = 1;
				newDigit = 0;
			} else {
				remainder = 0;
			}
			finalRes.add(0, newDigit);
		}

		if (remainder == 1) {
			finalRes.add(0, remainder);
		}

		int ans[] = new int[finalRes.size()];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = finalRes.get(i);
		}

		return ans;
	}
}
