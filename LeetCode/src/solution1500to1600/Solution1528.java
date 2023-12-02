package solution1500to1600;

public class Solution1528 {
	public String restoreString(String s, int[] indices) {
		char[] output = new char[s.length()];
		for (int i = 0; i < indices.length; i++) {
			output[indices[i]] = s.charAt(i);
		}

		return new String(output);
	}
}
