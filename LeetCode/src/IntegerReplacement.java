
public class IntegerReplacement {
	public int integerReplacement(int n) {
		return calculate(n);	
	}
	
	public int calculate(long n) {
		int count = 0;
		while (n % 2 == 0) {
			++count;
			n /= 2;
		}

		if (n == 1) {
			return count;
		} else {
			return count + 1 + Math.min(calculate(n - 1), calculate(n + 1));
		}
	}
}
