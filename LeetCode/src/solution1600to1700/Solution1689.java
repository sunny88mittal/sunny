package solution1600to1700;

public class Solution1689 {

	public int minPartitions(String n) {
		int max = -1;
		for (char ch : n.toCharArray()) {
			int v = ch - '0';
			if (v > max) {
				max = v;
			}
		}
		return max;
	}
}
