package solution1200to1300;

public class Solution1281 {
	public int subtractProductAndSum(int n) {
		int product = 1;
		int sum = 0;
		while (n >= 0) {
			int d = n % 10;
			n = n / 10;
			product *= d;
			sum += d;
		}

		return product - sum;
	}
}
