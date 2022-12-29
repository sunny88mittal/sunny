
public class LongestRepeatingCharacterReplacement {

	public int characterReplacement(String s, int k) {
		int charCount[] = new int[26];
		int j = 0;
		int maxLength = 0;
		for (int i = 0; i < s.length(); i++) {
			++charCount[s.charAt(i) - 'A'];
			while (getKValue(charCount) > k) {
				--charCount[s.charAt(j) - 'A'];
				++j;
			}
			int length = i - j + 1;
			if (maxLength < length) {
				maxLength = length;
			}
		}

		return maxLength;
	}

	public int getKValue(int charCount[]) {
		int max = 0;
		int sum = 0;
		for (int count : charCount) {
			if (count > max) {
				max = count;
			}
			sum += count;
		}
		return sum - max;
	}

	public static void main(String args[]) {
		LongestRepeatingCharacterReplacement obj = new LongestRepeatingCharacterReplacement();
		obj.characterReplacement("AABABBA", 1);
	}
}
