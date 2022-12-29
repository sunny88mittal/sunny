import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSUM15 {

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> triplets = new ArrayList<List<Integer>>();
		Set<String> tripleStringSet = new HashSet<String>();

		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length - 1; j++) {
				int sum = nums[i] + nums[j];
				int index = Arrays.binarySearch(nums, j + 1, nums.length, -sum);
				if (index > j) {
					int[] tripletArray = new int[] { nums[i], nums[j], nums[index] };
					Arrays.sort(tripletArray);
					String tripletString = Arrays.toString(tripletArray);
					if (!tripleStringSet.contains(tripletString)) {
						List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[index]);
						triplets.add(triplet);
					}
					tripleStringSet.add(tripletString);
				}
			}
		}

		return triplets;
	}

	public static void main(String args[]) {
		ThreeSUM15 obj = new ThreeSUM15();
		obj.threeSum(new int[] { -1, 0, 1, 2, -1, -4 });
	}
}
