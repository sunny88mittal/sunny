import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations2 {

	private Set<String> permutationsHash = new HashSet<String>();

	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> permutations = permuteHelper(nums);
		List<List<Integer>> nonDuplicates = new ArrayList<List<Integer>>();
		for (List<Integer> permutation : permutations) {
			if (!checkIfPermutationExists(permutation)) {
				nonDuplicates.add(permutation);
			}
		}
		return nonDuplicates;
	}

	private List<List<Integer>> permuteHelper(int[] nums) {
		List<List<Integer>> permutations = new ArrayList<List<Integer>>();
		if (nums.length == 1) {
			List<Integer> permutes = new ArrayList<Integer>();
			permutes.add(nums[0]);
			permutations.add(permutes);
			return permutations;
		}

		for (int i = 0; i < nums.length; i++) {
			if (i > 0 && nums[i] == nums[0]) {
				continue;
			}
			int temp = nums[0];
			nums[0] = nums[i];
			nums[i] = temp;

			List<List<Integer>> subPermutes = permuteHelper(Arrays.copyOfRange(nums, 1, nums.length));
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

	private boolean checkIfPermutationExists(List<Integer> permutation) {
		String s = "";
		for (Integer i : permutation) {
			s += i + ",";
		}

		if (permutationsHash.contains(s)) {
			return true;
		}

		permutationsHash.add(s);
		return false;
	}

	public static void main(String args[]) {
		Permutations2 obj = new Permutations2();
		int[] nos = new int[] { 1, 1, 2 };
		obj.permuteUnique(nos);
	}
}
