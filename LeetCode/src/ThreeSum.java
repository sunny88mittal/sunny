import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ThreeSum {

	private static int[] findThreeSum(int[] nums) {
		int length = nums.length;
		Map<Integer, List<Integer>> elementsIndexMap = new HashMap<Integer, List<Integer>>();

		for (int i = 0; i < length; i++) {
			int element = nums[i];
			List<Integer> indexes = elementsIndexMap.get(element);
			if (indexes == null) {
				indexes = new LinkedList<Integer>();
				elementsIndexMap.put(element, indexes);
			}
			indexes.add(i);
		}

		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				int sum = nums[i] + nums[j];
				List<Integer> indexes = elementsIndexMap.get(-sum);
				for (int index : indexes) {
					if (index != i && index != j) {
						return new int[] { nums[i], nums[j], nums[index] };
					}
				}
			}
		}

		return null;
	}
}
