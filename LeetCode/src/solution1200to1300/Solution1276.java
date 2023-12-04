package solution1200to1300;

import java.util.ArrayList;
import java.util.List;

public class Solution1276 {
	public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
		List<Integer> result = new ArrayList<>();
		if (4 * cheeseSlices - tomatoSlices < 0 || tomatoSlices % 2 == 1) {
			return result;
		}

		int smallBurgers = (4 * cheeseSlices - tomatoSlices) / 2;
		int largeBurgers = cheeseSlices - smallBurgers;

		if (smallBurgers >= 0 && largeBurgers >= 0) {
			result.add(largeBurgers);
			result.add(smallBurgers);
		}

		return result;
	}
}
