package solution300to400;

public class Solution318 {

	public int maxProduct(String[] words) {
		int n = words.length;
		int[] bitWiseVector = new int[n];

		for (int i = 0; i < n; i++) {
			for (char ch : words[i].toCharArray()) {
				bitWiseVector[i] |= 1 << ch - 'a';
			}
		}

		int maxProduct = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				int product = words[i].length() * words[j].length();
				if ((bitWiseVector[i] & bitWiseVector[j]) == 0 && product > maxProduct) {
					maxProduct = product;
				}
			}
		}

		return maxProduct;
	}

	public static void main(String args[]) {
		Solution318 obj = new Solution318();
		String arr[] = new String[] { "abcw", "baz", "foo", "bar", "xtfn", "abcdef" };
		obj.maxProduct(arr);
	}
}
