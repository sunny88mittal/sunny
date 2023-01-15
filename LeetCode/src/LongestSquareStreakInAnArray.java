import java.util.HashSet;
import java.util.Set;

public class LongestSquareStreakInAnArray {

	public int longestSquareStreak(int[] nums) {
		Set<Long> numbersSet = new HashSet<Long>();
		for (int i : nums) {
			numbersSet.add((long) i);
		}

		int maxStreak = -1;
		for (int i = 0; i < nums.length; i++) {
			long num = nums[i];
			if (!numbersSet.contains(num)) {
				continue;
			}

			int count = 1;
			long square = num * num;
			while (numbersSet.contains(square)) {
				numbersSet.remove(square);
				num = square;
				square = num * num;
				++count;
			}

			num = nums[i];
			double dSqrt = Math.sqrt(num);
			long iSqrt = (long) dSqrt;
			while (dSqrt - iSqrt == 0 && numbersSet.contains(iSqrt)) {
				numbersSet.remove(iSqrt);
				num = iSqrt;
				dSqrt = Math.sqrt(num);
				iSqrt = (int) dSqrt;
				++count;
			}

			if (count > 1 && count > maxStreak) {
				maxStreak = count;
			}
		}

		return maxStreak;
	}
}
