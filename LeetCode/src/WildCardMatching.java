
public class WildCardMatching {

	public boolean isMatch(String text, String pattern) {
		if (pattern.isEmpty())
			return text.isEmpty();

		if (text.isEmpty() && pattern.charAt(0) == '*') {
			return isMatch(text, pattern.substring(1));
		}

		if (!text.isEmpty() && pattern.charAt(0) == '*') {
			return isMatch(text.substring(1), pattern) || isMatch(text, pattern.substring(1));
		}

		if (!text.isEmpty() && (text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '?')) {
			return isMatch(text.substring(1), pattern.substring(1));
		}

		return false;
	}
}
