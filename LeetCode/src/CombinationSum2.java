import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum2 {

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		return getUniqueLists(combinationSumHelper(candidates, 0, target));
	}

	public List<List<Integer>> combinationSumHelper(int[] candidates, int startAt, int target) {
		if (startAt >= candidates.length) {
			List<List<Integer>> combinaitonsLists = new ArrayList<List<Integer>>();
			return combinaitonsLists;
		}

		List<List<Integer>> combinaitonsLists = new ArrayList<List<Integer>>();
		int maxCountForCurrentNo = target / candidates[startAt];
		for (int i = 0; i <= Math.min(1, maxCountForCurrentNo); i++) {
			int valueFromCurrentNo = candidates[startAt] * i;
			List<Integer> combination = new ArrayList<Integer>();
			for (int j = 0; j < i; j++) {
				combination.add(candidates[startAt]);
			}

			if (target - valueFromCurrentNo == 0) {
				combinaitonsLists.add(combination);
			} else if (target - valueFromCurrentNo > 0) {
				List<List<Integer>> combinaitonsListsFurther = combinationSumHelper(candidates, startAt + 1,
						target - valueFromCurrentNo);
				for (List<Integer> combinationList : combinaitonsListsFurther) {
					if (combinationList.size() > 0) {
						combinationList.addAll(combination);
						combinaitonsLists.add(combinationList);
					}
				}
			}
		}
		return combinaitonsLists;
	}

	public List<List<Integer>> getUniqueLists(List<List<Integer>> combinations) {
		List<List<Integer>> uniqueLists = new ArrayList<List<Integer>>();
		Set<String> hashes = new HashSet<String>();
		for (List<Integer> combination : combinations) {
			String hash = "";
			for (Integer no : combination) {
				hash += no;
			}

			if (!hashes.contains(hash)) {
				uniqueLists.add(combination);
				hashes.add(hash);
			}
		}
		return uniqueLists;
	}
}
