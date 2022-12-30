import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

	public List<List<String>> partition(String s) {
		if (s.length() == 1) {
			List<String> combinations = new ArrayList<String>();
			combinations.add(s);
			List<List<String>> combinationsLists = new ArrayList<List<String>>();
			combinationsLists.add(combinations);
			return combinationsLists;
		}

		List<List<String>> combinationsLists = new ArrayList<List<String>>();

		for (int i = 1; i <= s.length(); i++) {
			String left = s.substring(0, i);
			String right = s.substring(i, s.length());

			boolean isLeftPalindrome = isPalindrome(left);
			List<List<String>> rightCombinationsLists = partition(right);

			if (isLeftPalindrome) {
				for (List<String> rightCombinationList : rightCombinationsLists) {
					rightCombinationList.add(0, left);
					combinationsLists.add(rightCombinationList);
				}
			}

			if (isLeftPalindrome && right.length() == 0) {
				List<String> combination = new ArrayList<String>();
				combination.add(left);
				combinationsLists.add(combination);
			}
		}

		return combinationsLists;
	}

	private boolean isPalindrome(String s) {
		int i = 0;
		int j = s.length() - 1;

		while (i <= j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
			++i;
			--j;
		}

		return true;
	}

	public static void main(String args[]) {
		PalindromePartitioning obj = new PalindromePartitioning();
		obj.partition("abbab");
	}
}
