
public class RemoveDigitFromNumberToMaximizeResult {
	public String removeDigit(String number, char digit) {
		String s = null;
		for (int i = 0; i < number.length(); i++) {
			if (number.charAt(i) == digit) {
				String replaced = number.substring(0, i) + number.substring(i + 1, number.length());
				if (s == null || replaced.compareTo(s) > 0) {
					s = replaced;
				}
			}
		}

		return s;
	}
}
