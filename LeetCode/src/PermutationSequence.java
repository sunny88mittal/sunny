
public class PermutationSequence {

	public String getPermutation(int n, int k) {
		Integer s = 0;
		for (int i = 1; i <= n; i++) {
			s = s * 10 + i;
		}

		return getPermutation(s + "", k);
	}

	private String getPermutation(String s, int k) {
		if (k == 1) {
			return s;
		}

		int length = s.length();
		int i = 0;
		int factorial = getFactorial(length - 1);
		while (factorial < k) {
			i++;
			k = k - factorial;
		}

		s = s.charAt(i) + getPermutation(s.substring(0, i) + s.substring(i + 1, s.length()), k);

		return s;
	}

	private int getFactorial(int n) {
		int factorial = 1;
		for (int i = 1; i <= n; i++) {
			factorial *= i;
		}
		return factorial;
	}

	public static void main(String args[]) {
		PermutationSequence obj = new PermutationSequence();
		obj.getPermutation(3, 4);
	}
}
