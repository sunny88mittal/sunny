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

public class CBCPaddingOracleDecrypt {

	private static byte[] guessArray = new byte[16];
	
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

		// message to encrypt
		String messageToDecrypt = "aqzwIxKHO/2HGlhYAwqG/up9a2GjEzmwTI8rPNSl3A4=";
		byte[] decodedBytes = Base64.decodeBase64(messageToDecrypt
				.getBytes("UTF-8"));
	    decryptUsingPaddingOracle(cipher, decodedBytes); 
	}

	private static void decryptUsingPaddingOracle(Cipher cipher,
			byte[] bytesToDecrypt) throws IllegalBlockSizeException,
			BadPaddingException, UnsupportedEncodingException {
		for (int j=0; j<16; j++) {
			byte[] bytesToDecryptCopy = getupdateByteArray(bytesToDecrypt, j);
			int i=0;
			for (i=0; i <128; i++) {
				if ( i == j+1) {
					continue;
				}
				bytesToDecryptCopy[15-j] = bytesToDecrypt[15-j]; //Reset to initial value
				bytesToDecryptCopy[15-j] = (byte) (bytesToDecryptCopy[15-j] ^ ((byte) i) ^ (j+1)); //c[15] ^ g ^ j
				
				//THIS LINE SHOWS WE CAN MODIFY THE CIPHERTEXT, STORE IN DB, USE SOME OPERATION IN DOMAIN SO
				//THAT THE CIPHER GETS DECRYPTED AND THAN TRY AGAIN
				bytesToDecryptCopy = Base64.decodeBase64(Base64.encodeBase64(bytesToDecryptCopy)); 
				try {
					cipher.doFinal(bytesToDecryptCopy);
				} catch (BadPaddingException bpe) {
					continue;
				}
				guessArray[15-j] = (byte) i;
				if (16 <i && i<128) {
					System.out.println((char)i);
				} else {
					System.out.println("pad");
				}
				break;
			}
			
			if (i == 128) {
				guessArray[15-j] = (byte) (j+1);
				System.out.println("pad");
			}
		}
	}
	
	private static byte[] getupdateByteArray(byte[] bytesToDecrypt, int j) {
		byte[] bytesToDecryptCopy = bytesToDecrypt.clone();
		for (int k=0; k<j; k++) {
			bytesToDecryptCopy[15-k] = (byte) (bytesToDecryptCopy[15-k] ^ guessArray[15-k] ^ (j+1)); //c[i] ^ g[i] ^ j
		}
		return bytesToDecryptCopy;
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
