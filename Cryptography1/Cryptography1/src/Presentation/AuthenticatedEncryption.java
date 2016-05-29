package Presentation;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AuthenticatedEncryption {

	public static void main(String[] args) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException {
		String key = "36f18357be4dbd77f050515c73fcf9f2";
	    String message = "e46218c0a53cbeca695ae45faa8952aa0e311bde9d4e01726d3184c34451d2d4d6d7";

		SecretKey secretKey = new SecretKeySpec(convertToByteArray(key), "AES");
		GCMParameterSpec s = new GCMParameterSpec(128, convertToByteArray("4ca00ff4c898d61e1edbf1800618fb28"));
		//IvParameterSpec parameterSpec = new IvParameterSpec(convertToByteArray("4ca00ff4c898d61e1edbf1800618fb28"));

		Cipher cipherObj = Cipher.getInstance("AES/GCM/NoPadding");
		cipherObj.init(Cipher.ENCRYPT_MODE, secretKey, s);

		System.out.println("Plaintext Length:" +  convertToByteArray(message).length);
		
		byte[] cipherByteArray = cipherObj.doFinal(convertToByteArray(message));

		System.out.println("Cipher length:" + cipherByteArray.length);
	}

	private static byte[] convertToByteArray(String hexStr) {
		int length = hexStr.length();
		byte[] byteArr = new byte[length/2];
		for (int i = 0; i <length/2; i++) {
			int currPointer = i * 2;
			String encLetter = hexStr.substring(currPointer, currPointer + 2);
			byteArr[i] = (byte) Integer.parseInt(encLetter, 16);
		}
		return byteArr;
	}
	
	private static void printFinalString (byte[] decByteArray) {
		String str = "";
		for (int i=0; i<decByteArray.length; i++) {
			int val = decByteArray[i] < 0 ? decByteArray[i] + 256 : decByteArray[i];
			str += (char)val;
		}
		System.out.println(str);
	}
}
