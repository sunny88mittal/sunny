package solution2100to2200;

public class Solution2114 {
	public int mostWordsFound(String[] sentences) {
		int max = 0;
		for (String s : sentences) {
			int cc = s.split(" ").length;
			if (cc > max) {
				max = cc;
			}
		}

		return max;
	}
}
