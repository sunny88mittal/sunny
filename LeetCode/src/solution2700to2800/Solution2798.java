package solution2700to2800;

public class Solution2798 {
	public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
		int count = 0;
		for (int h : hours) {
			if (h >= target) {
				++count;
			}
		}
		return count;
	}
}
