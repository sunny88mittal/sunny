package Presentation;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class ModifiedCipherDecryption {

	public static void main(String args[]) throws UnsupportedEncodingException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		// Get the secret key
		SecretKey secretKey = generateSecretKey("sunny", "testdomain");

		// Create the cipher and initialize it
		byte[] INIT_VECTOR_16 = new byte[] { (byte) 0x19, (byte) 0xA6,
				(byte) 0x1C, (byte) 0x4F, (byte) 0xFC, (byte) 0x9B,
				(byte) 0xD0, (byte) 0xEF, (byte) 0xA8, (byte) 0x6A,
				(byte) 0x2D, (byte) 0xDE, (byte) 0x32, (byte) 0xCD,
				(byte) 0x6C, (byte) 0xB0 };
		IvParameterSpec parameterSpec = new IvParameterSpec(INIT_VECTOR_16);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);

		// message to decrypt
		String messageToDecrypt = "kgHah531VNGvEdS1RtYP9vo2NrARbShb6sh2fw5M98E=";
		byte[] decodedBytes = Base64.decodeBase64(messageToDecrypt
				.getBytes("UTF-8"));
		//CHANGED THE 15th BYTE, NEED TO CHECK HOW TO DIRECTLY MODIFY THE BASE 64 ENCODED STRING
		decodedBytes[15] = (byte) 243;
		decodedBytes = Base64.decodeBase64(Base64.encodeBase64(decodedBytes));

		// Decrypt the cipher
		byte[] decryptedBytes = cipher.doFinal(decodedBytes);
		String decryptedString = new String(decryptedBytes, "UTF-8");
		System.out.println(decryptedString);
	}

	private static SecretKey generateSecretKey(String userSeed,
			String domainName) throws UnsupportedEncodingException,
			NoSuchAlgorithmException {
		byte[] seed = (userSeed + domainName).getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("SHA1");
		md.update(seed);
		byte[] hash = md.digest();

		// Retrieving the first 16 bytes of the SHA1 hash as for AES encryption
		// we require key of 16 bytes length.
		byte[] key = new byte[16];
		System.arraycopy(hash, 0, key, 0, key.length);

		SecretKey secretKey = new SecretKeySpec(key, "AES");
		return secretKey;
	}
}
