package SRM662;

public class Hexspeak {

	public 	String decode(long ciphertext) {
		String hexString = Long.toHexString(ciphertext);
		hexString = hexString.replace('0', 'O');
		hexString = hexString.replace('1', 'I');
		for (char c = '2'; c<='9'; c++) {
			if (hexString.lastIndexOf(c) != -1) {
				return "Error!";
			}
		}
		return hexString.toUpperCase();
	}
}
