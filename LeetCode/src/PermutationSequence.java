
public class PermutationSequence {

	private int[] factotrialTable = new int[10];

	public String getPermutation(int n, int k) {
		Integer s = 0;

		factotrialTable[1] = 1;
		for (int i = 2; i <= 9; i++) {
			factotrialTable[i] = i * factotrialTable[i - 1];
		}

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
		int factorial = factotrialTable[length - 1];

		if (k > factorial) {
			i = k / factorial;
			if (k % factorial == 0) {
				--i;
			}
			k = k - (i * factorial);
		}

		s = s.charAt(i) + getPermutation(s.substring(0, i) + s.substring(i + 1, s.length()), k);

		return s;
	}

	public static void main(String args[]) {
		PermutationSequence obj = new PermutationSequence();
		obj.getPermutation(3, 4);
	}
}
