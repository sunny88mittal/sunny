import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> permutations = new ArrayList<List<Integer>>();
		if (nums.length == 1) {
			List<Integer> permutes = new ArrayList<Integer>();
			permutes.add(nums[0]);
			permutations.add(permutes);
			return permutations;
		}

		for (int i = 0; i < nums.length; i++) {
			int temp = nums[0];
			nums[0] = nums[i];
			nums[i] = temp;

			List<List<Integer>> subPermutes = permute(Arrays.copyOfRange(nums, 1, nums.length));
			for (List<Integer> permutation : subPermutes) {
				permutation.add(0, nums[0]);
				permutations.add(permutation);
			}

			temp = nums[i];
			nums[i] = nums[0];
			nums[0] = temp;
		}

		return permutations;
	}
}
