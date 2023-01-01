import java.util.HashMap;
import java.util.Map;

public class FourSum2 {

	public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
		int count = 0;
		Map<Integer, Integer> mapForArray1And2 = new HashMap<Integer, Integer>();
		Map<Integer, Integer> mapForArray3And4 = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums1.length; i++) {
			for (int j = 0; j < nums2.length; j++) {
				int sum = nums1[i] + nums2[j];
				mapForArray1And2.put(sum, mapForArray1And2.getOrDefault(sum, 0) + 1);
			}
		}

		for (int i = 0; i < nums3.length; i++) {
			for (int j = 0; j < nums4.length; j++) {
				int sum = nums3[i] + nums4[j];
				mapForArray3And4.put(sum, mapForArray3And4.getOrDefault(sum, 0) + 1);
			}
		}

		for (int key : mapForArray1And2.keySet()) {
			Integer inputCount = mapForArray1And2.get(key);
			Integer matchingCount = mapForArray3And4.get(0 - key);
			if (matchingCount != null) {
				count += inputCount * matchingCount;
			}
		}

		return count;
	}
}
