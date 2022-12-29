import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {

	public int[] twoSum(int[] nums, int target) {
		int[] response = new int[2];
		Map<Integer, List<Integer>> numsMap = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < nums.length; i++) {
			List<Integer> indexes = numsMap.get(nums[i]);
			if (indexes == null) {
				indexes = new ArrayList<Integer>();
			}
			indexes.add(i);
			numsMap.put(nums[i], indexes);
		}

		for (int i = 0; i < nums.length; i++) {
			int toGet = target - nums[i];

			List<Integer> indexes = numsMap.get(nums[i]);
			if (indexes.size() > 1) {
				indexes.remove(0);
			} else {
				numsMap.remove(nums[i]);
			}

			indexes = numsMap.get(toGet);
			if (indexes != null) {
				response[0] = i;
				response[1] = indexes.get(0);
				break;
			}
		}

		return response;
	}

	public static void main(String args[]) {
		TwoSum obj = new TwoSum();
		obj.twoSum(new int[] { 3, 2, 4 }, 6);
	}
}
