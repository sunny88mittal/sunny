import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

	public boolean wordBreak(String s, List<String> wordDict) {
		int length = s.length();
		boolean[] possibleFor = new boolean[length];
		Set<String> dictionary = new HashSet<String>();
		for (String str : wordDict) {
			dictionary.add(str);
		}

		for (int i = length - 1; i >= 0; i--) {
			possibleFor[i] |= dictionary.contains(s.substring(i, length));
			for (int j = i; j < length; j++) {
				boolean currentPossibility = dictionary.contains(s.substring(i, j + 1));
				if (j + 1 < length) {
					currentPossibility &= possibleFor[j + 1];
				}
				possibleFor[i] |= currentPossibility;
			}
		}

		return possibleFor[0];
	}

	public static void main(String args[]) {
		WordBreak wb = new WordBreak();
		wb.wordBreak("catsandog", Arrays.asList(new String[] { "cats","dog","sand","and","cat" }));
	}
}
