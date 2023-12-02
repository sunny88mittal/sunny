package solution1400to1500;

import java.util.ArrayList;
import java.util.List;

public class Solution1431 {
	public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
		List<Boolean> ans = new ArrayList<>();
		int max = 0;
		for (int i = 0; i < candies.length; i++) {
			if (candies[i] > max) {
				max = candies[i];
			}
		}

		for (int i = 0; i < candies.length; i++) {
			if (candies[i] + extraCandies >= max) {
				ans.add(true);
			} else {
				ans.add(false);
			}
		}

		return ans;
	}
}
