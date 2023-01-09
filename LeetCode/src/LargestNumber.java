import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {

	class CustomComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			String s1 = o1 + "";
			String s2 = o2 + "";

			String s1AsPrefix = s1 + s2;
			String s2AsPrefix = s2 + s1;

			return s2AsPrefix.compareTo(s1AsPrefix);
		}
	}

	public String largestNumber(int[] nums) {
		CustomComparator cmp = new CustomComparator();
		Integer[] sorted = new Integer[nums.length];
		for (int i = 0; i < nums.length; i++) {
			sorted[i] = nums[i];
		}
		Arrays.sort(sorted, cmp);
		String s = "";
		for (Integer a : sorted) {
			s += a;
		}

		while (s.length() > 1 && s.startsWith("0")) {
			s = s.substring(1);
		}

		return s;
	}
}
