package solution2600to2700;

public class Solution2652 {
	public int sumOfMultiples(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
				sum += i;
			}
		}

		return sum;
	}
}
