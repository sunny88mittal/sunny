import java.util.ArrayList;
import java.util.List;

public class Subsets {

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> subsets = new ArrayList<List<Integer>>();
		int totalCount = (int) Math.pow(2, nums.length);
		for (int i = 0; i < totalCount; i++) {
			List<Integer> set = new ArrayList<Integer>();
			int toCheck = i;
			for (int j = 0; j < nums.length; j++) {
				if ((1 & toCheck) == 1) {
					set.add(nums[j]);
				}
				toCheck = toCheck >> 1;
			}
			subsets.add(set);
		}
		return subsets;
	}

	public static void main(String args[]) {
		Subsets obj = new Subsets();
		int[] input = new int[] { 1, 2, 3 };
		System.out.println(obj.subsets(input));
	}
}
