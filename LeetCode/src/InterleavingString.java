
/** https://leetcode.com/problems/interleaving-string/ **/

public class InterleavingString {

	public boolean isInterleave(String s1, String s2, String s3) {
		int lens1 = s1.length();
		int lens2 = s2.length();
		int lens3 = s3.length();

		if (lens3 != lens1 + lens2) {
			return false;
		}

		boolean calculated[][] = new boolean[lens1 + 1][lens2 + 1];
		boolean value[][] = new boolean[lens1 + 1][lens2 + 1];

		return checkIsInterleave(s1, s2, s3, 0, 0, 0, calculated, value);
	}

	private boolean checkIsInterleave(String s1, String s2, String s3, int s1i, int s2i, int s3i,
			boolean calculated[][], boolean value[][]) {
		if (calculated[s1i][s2i]) {
			return value[s1i][s2i];
		}

		if (s3i == s3.length()) {
			return true;
		}

		boolean ans = false;
		if (s1i < s1.length()) {
			ans |= s1.charAt(s1i) == s3.charAt(s3i)
					&& checkIsInterleave(s1, s2, s3, s1i + 1, s2i, s3i + 1, calculated, value);
		}

		if (s2i < s2.length()) {
			ans |= s2.charAt(s2i) == s3.charAt(s3i)
					&& checkIsInterleave(s1, s2, s3, s1i, s2i + 1, s3i + 1, calculated, value);
		}

		calculated[s1i][s2i] = true;
		value[s1i][s2i] = ans;
		return ans;
	}

	public static void main(String args[]) {
		InterleavingString obj = new InterleavingString();
		System.out.println(obj.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
		System.out.println(obj.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
		System.out.println(obj.isInterleave("", "", ""));
	}
}