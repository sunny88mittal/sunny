package solution2900to3000;

public class Solution2957 {
	public int removeAlmostEqualCharacters(String word) {
		int count = 0;
		int i = 1;
		while (i < word.length()) {
			boolean leftEqual = Math.abs(word.charAt(i - 1) - word.charAt(i)) <= 1 ? true : false;
			boolean rightEqual = false;
			if (i + 1 < word.length()) {
				rightEqual = Math.abs(word.charAt(i + 1) - word.charAt(i)) <= 1 ? true : false;
			}

			if (leftEqual & rightEqual) {
				++count;
				i += 2;
			} else if (leftEqual & !rightEqual) {
				++count;
				i += 1;
			} else if (!leftEqual & rightEqual) {
				i += 1;
			} else {
				i += 2;
			}
		}

		return count;
	}
}
