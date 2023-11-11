package solution200to300;

import java.util.HashSet;
import java.util.Set;

public class Solution202 {
	public boolean isHappy(int n) {
		Set<Integer> nos = new HashSet<>();
		int squaresSum = n;
		while (true) {
			squaresSum = getSquareSum(squaresSum);
			if (squaresSum == 1) {
				return true;
			}

			if (nos.contains(squaresSum)) {
				return false;
			}

			nos.add(squaresSum);
		}
	}

	public int getSquareSum(int n) {
		int sum = 0;
		while (n > 9) {
			int digit = n % 10;
			sum += digit * digit;
			n = n / 10;
		}
		sum += n * n;
		return sum;
	}

	public static void main(String args[]) {
		Solution202 obj = new Solution202();
		obj.isHappy(19);
	}
}
