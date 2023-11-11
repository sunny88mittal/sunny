package solution100to200;

import java.util.ArrayList;
import java.util.List;

public class Solution119 {
	public List<Integer> getRow(int numRows) {
		List<Integer> currRow = new ArrayList<>();
		currRow.add(1);

		for (int i = 1; i <= numRows; i++) {
			List<Integer> nextRow = new ArrayList<>();
			nextRow.add(1);
			for (int j = 0; j < currRow.size() - 1; j++) {
				nextRow.add(currRow.get(j) + currRow.get(j + 1));
			}
			nextRow.add(1);
			currRow = nextRow;
		}

		return currRow;
	}
}
