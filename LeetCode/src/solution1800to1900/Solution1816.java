package solution1800to1900;

public class Solution1816 {
	public String truncateSentence(String s, int k) {
		String[] words = s.split(" ");
		String ans = "";
		for (int i = 0; i < k - 1; i++) {
			ans += words[i] + " ";
		}

		return ans + words[k - 1];
	}
}
