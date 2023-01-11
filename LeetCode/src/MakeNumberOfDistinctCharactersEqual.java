
public class MakeNumberOfDistinctCharactersEqual {

	public boolean isItPossible(String word1, String word2) {
		int[] word1Count = new int[26];
		int[] word2Count = new int[26];

		int uniqueWord1 = 0;
		int uniqueWord2 = 0;

		for (char ch : word1.toCharArray()) {
			++word1Count[ch - 'a'];
			if (word1Count[ch - 'a'] == 1) {
				++uniqueWord1;
			}
		}

		for (char ch : word2.toCharArray()) {
			++word2Count[ch - 'a'];
			if (word2Count[ch - 'a'] == 1) {
				++uniqueWord2;
			}
		}

		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				if (word1Count[i] > 0 && word2Count[j] > 0) {
					int uniqueWord1Poss = uniqueWord1;
					int uniqueWord2Poss = uniqueWord2;

					if (i != j) {
						if (word1Count[i] == 1) {
							--uniqueWord1Poss;
						}

						if (word1Count[j] == 0) {
							++uniqueWord1Poss;
						}

						if (word2Count[i] == 0) {
							++uniqueWord2Poss;
						}

						if (word2Count[j] == 1) {
							--uniqueWord2Poss;
						}
					}

					if (uniqueWord1Poss == uniqueWord2Poss) {
						return true;
					}
				}
			}
		}

		return false;
	}
}
