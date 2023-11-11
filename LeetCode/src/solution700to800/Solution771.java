package solution700to800;

public class Solution771 {
	public int numJewelsInStones(String jewels, String stones) {
		boolean map[] = new boolean[100];

		for (char c : jewels.toCharArray()) {
			map[c - 'A'] = true;
		}

		int count = 0;
		for (char c : stones.toCharArray()) {
			if (map[c - 'A']) {
				++count;
			}
		}

		return count;
	}
}
