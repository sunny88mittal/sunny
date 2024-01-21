package solution3000to3100;

public class Solution3014 {
	public int minimumPushes(String word) {
		int count = 0;
		int length = word.length();
		int counter = 1;
		while (length >= 8) {
			count += 8 * counter;
			length -= 8;
			++counter;
		}
		count += length * counter;

		return count;
	}
}
