package solution2900to3000;

import java.util.HashSet;
import java.util.Set;

public class Solution2956 {
	public int[] findIntersectionValues(int[] nums1, int[] nums2) {
		int[] count = new int[2];

		Set<Integer> s1 = new HashSet<>();
		Set<Integer> s2 = new HashSet<>();

		for (int num : nums1) {
			s1.add(num);
		}

		for (int num : nums2) {
			s2.add(num);
		}

		for (int num : nums1) {
			if (s2.contains(num)) {
				++count[0];
			}
		}

		for (int num : nums2) {
			if (s1.contains(num)) {
				++count[1];
			}
		}

		return count;
	}
}
