package solution100to200;

import java.util.ArrayList;
import java.util.List;

public class Solution118 {
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> sol = new ArrayList<>();

		List<Integer> firstRow = new ArrayList<>();
		firstRow.add(1);
		sol.add(firstRow);

		for (int i = 2; i <= numRows; i++) {
			List<Integer> nextRow = new ArrayList<>();
			nextRow.add(1);
			List<Integer> prevRow = sol.get(i - 1);
			for (int j = 0; j < prevRow.size() - 1; j++) {
				nextRow.add(prevRow.get(j) + prevRow.get(j + 1));
			}
			nextRow.add(1);
			sol.add(nextRow);
		}

		return sol;
	}
}
