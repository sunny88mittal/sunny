public class DecodeWays {

	public int numDecodings(String s) {
		int length = s.length();
		int[] count = new int[length];
		count[length - 1] = Integer.parseInt(s.charAt(length - 1) + "") != 0 ? 1 : 0;

		for (int i = s.length() - 2; i >= 0; i--) {
			int digit = Integer.parseInt(s.charAt(i) + "");
			if (digit == 0) {
				continue;
			}

			count[i] = count[i + 1];
			digit = 10 * digit + Integer.parseInt(s.charAt(i + 1) + "");
			if (digit <= 26) {
				count[i] += (i + 2) > (length - 1) ? 1 : count[i + 2];
			}
		}

		return count[0];
	}
}
