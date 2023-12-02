package solution2000to2100;

public class Solution2011 {
	public int finalValueAfterOperations(String[] operations) {
		int count = 0;
		for (String op : operations) {
			if (op.contains("++")) {
				++count;
			} else {
				--count;
			}
		}

		return count;
	}
}
