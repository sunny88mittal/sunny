package SRM147;

public class CCipher {
	
	public String decode(String cipherText, int shift) {
	   	char[] chars = cipherText.toCharArray();
	   	cipherText = "";
	   	int length = chars.length;
	   	int A = 'A';
	   	for (int i=0; i<length; i++) {
	   		int intValue = chars[i] - shift;
	   		if (intValue < A) {
	   			intValue = intValue + 26;
	   		}
	   		cipherText += (char) intValue;
	   	}
	   	
	   	return cipherText;
	}
}
