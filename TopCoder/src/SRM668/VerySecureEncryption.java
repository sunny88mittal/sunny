package SRM668;

public class VerySecureEncryption {

	public String encrypt(String message, int[] key, int K) {
		char[] messageO = message.toCharArray();
		char[] messageC = message.toCharArray();
		for (int i=0; i<K; i++) {
			for (int j=0; j<key.length; j++) {
				messageC[key[j]] = messageO[j];
			}
			messageO = messageC.clone();
		}
		return new String(messageO);
	}
	
	public static void main (String args[]) {
		VerySecureEncryption obj = new VerySecureEncryption();
		obj.encrypt("abcde", new int[]{4, 3, 2, 1, 0}, 2);
	}
}
