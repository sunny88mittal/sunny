import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {

	private static String[] strs = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

	public List<String> letterCombinations(String digits) {
		if (digits.length() == 0) {
			return new ArrayList<>();
		}

		int digit = Integer.parseInt(digits.charAt(0) + "");
		List<String> nextResults = letterCombinations(digits.substring(1));
		String chars = strs[digit];
		List<String> results = new ArrayList<String>();
		for (char ch : chars.toCharArray()) {
			if (nextResults.size() != 0) {
				for (String str : nextResults) {
					results.add(ch + str);
				}
			} else {
				results.add(ch + "");
			}
		}

		return results;
	}
}
