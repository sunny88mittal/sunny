package solution1500to1600;

public class Solution1512 {
	public int numIdenticalPairs(int[] nums) {
		int count[] = new int[101];

		for (int n : nums) {
			++count[n];
		}

		int sum = 0;
		for (int c : count) {
			sum += c > 0 ? c * (c - 1) / 2 : 0;
		}

		return sum;
	}
}
