package SRM656;

public class CorruptedMessage {

	public String reconstructMessage(String s, int k) {
		int[] count = new int[26];
		char[] sc = s.toCharArray();
		for (char ch : sc) {
			count[ch - 'a']++;
		}
		for (int i=0; i<26; i++) {
			if (count[i]  == s.length() - k) {
				for (int j=0; j<sc.length; j++) {
					sc[j] = (char)('a' + i);
				}
				break;
			}
		}
		return new String(sc);
	}
}
