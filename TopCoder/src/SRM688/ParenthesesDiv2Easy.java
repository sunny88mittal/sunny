package SRM688;

public class ParenthesesDiv2Easy {

	public int getDepth(String s) {
		int max = 0, temp = 0;
		char[] cs = s.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			if (cs[i] == '(') {
				temp++;
				max = Math.max(max, temp);
			} else {
				temp--;
			}
		}

		return max;
	}
}
