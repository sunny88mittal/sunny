
public class IndexOfFirstOcurrence {

	public int strStr(String haystack, String needle) {
		int hl = haystack.length();
		int nl = needle.length();
		for (int i = 0; i < hl - nl; i++) {
			boolean found = true;
			for (int j = 0; j < nl; j++) {
				if (haystack.charAt(j + i) != needle.charAt(j)) {
					found = false;
					break;
				}
			}
			if (found) {
				return i;
			}
		}

		return -1;
	}
}
