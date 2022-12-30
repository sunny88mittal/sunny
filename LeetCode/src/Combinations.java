import java.util.ArrayList;
import java.util.List;

public class Combinations {

	public List<List<Integer>> combine(int n, int k) {
		return combineHelper(1, n, k);
	}

	public List<List<Integer>> combineHelper(int start, int n, int k) {
		List<List<Integer>> combinaitons = new ArrayList<List<Integer>>();

		if (k == 0) {
			return combinaitons;
		}

		if (n + 1 - start == k) {
			List<Integer> nos = new ArrayList<Integer>();
			for (int i = start; i <= n; i++) {
				nos.add(i);
			}
			combinaitons.add(nos);
			return combinaitons;
		}

		// Use current no
		List<Integer> combinationsWithNo = new ArrayList<Integer>();
		combinationsWithNo.add(start);
		List<List<Integer>> combinaitonsWithNoParts = combineHelper(start + 1, n, k - 1);
		if (combinaitonsWithNoParts.size() > 0) {
			for (List<Integer> list : combinaitonsWithNoParts) {
				list.addAll(combinationsWithNo);
				combinaitons.add(list);
			}
		} else {
			combinaitons.add(combinationsWithNo);
		}

		// Skip current no
		List<List<Integer>> combinaitonsWithoutNo = combineHelper(start + 1, n, k);
		combinaitons.addAll(combinaitonsWithoutNo);

		return combinaitons;
	}

	public static void main(String args[]) {
		Combinations obj = new Combinations();
		obj.combine(4, 2);
	}
}
