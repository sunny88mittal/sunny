import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		return combinationSumHelper(candidates, 0, target);
	}

	public List<List<Integer>> combinationSumHelper(int[] candidates, int startAt, int target) {
		if (startAt >= candidates.length) {
			List<List<Integer>> combinaitonsLists = new ArrayList<List<Integer>>();
			return combinaitonsLists;
		}

		List<List<Integer>> combinaitonsLists = new ArrayList<List<Integer>>();
		int maxCountForCurrentNo = target / candidates[startAt];
		for (int i = 0; i <= maxCountForCurrentNo; i++) {
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
}
