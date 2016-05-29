package week2;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCBCTest {

	public static void main(String[] args) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException {
		long startTime = System.currentTimeMillis();
		String key = "36f18357be4dbd77f050515c73fcf9f2";
	    String message = "e46218c0a53cbeca695ae45faa8952aa0e311bde9d4e01726d3184c34451d2d4d6d7";

		SecretKey secretKey = new SecretKeySpec(convertToByteArray(key), "AES");
		IvParameterSpec parameterSpec = new IvParameterSpec(
				convertToByteArray("4ca00ff4c898d61e1edbf1800618fb28"));

		Cipher cipherObj = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipherObj.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);

		System.out.println("Plaintext Length:" +  convertToByteArray(message).length);
		
		byte[] cipherByteArray = cipherObj.doFinal(convertToByteArray(message));
		
		//System.out.println(System.currentTimeMillis() - startTime);
		
		System.out.println("Cipher length:" + cipherByteArray.length);
		
        //printFinalString(decByteArray);
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
