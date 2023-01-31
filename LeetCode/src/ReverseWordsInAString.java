
public class ReverseWordsInAString {
	public String reverseWords(String s) {
		String output = "";
		s = reverseString(s);
		String words[] = s.split(" ");
		for (String word : words) {
			word = word.replace(" ", "");
			if (word.length() > 0) {
				output += reverseString(word) + " ";
			}
		}

		return output.trim();
	}

	private String reverseString(String s) {
		String result = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			result += s.charAt(i);
		}
		return result;
	}
}
