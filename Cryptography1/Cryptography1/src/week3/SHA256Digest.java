package week3;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHA256Digest {

	public static void main (String args[]) throws IOException, NoSuchAlgorithmException {
        //String filename = "C:\\Personal Data\\Courses\\Computers\\Cryptography\\Cryptography\\Week 3\\Prog Assignment\\6 - 2 - Generic birthday attack (16 min).mp4";
		String filename = "C:\\Personal Data\\Courses\\Computers\\Cryptography\\Cryptography\\Week 3\\Prog Assignment\\6 - 1 - Introduction (11 min).mp4";
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));
		
		int totalBytes = in.available();
		int totalKbBlocks = totalBytes /1024;
		
		//Read all the bytes
		byte[] allBytes = new byte[totalBytes];
		in.read(allBytes);
		
		//Read the last block
		byte[] lastBlockbytesArray = new byte[totalBytes - totalKbBlocks * 1024];
		lastBlockbytesArray = Arrays.copyOfRange(allBytes, totalKbBlocks * 1024, totalBytes);
		
		//Compute the hash for the last block
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.update(lastBlockbytesArray);
		byte[] hashValue = digest.digest();
		
		byte[] byteBlock = new byte[1024];
		for (int i=totalKbBlocks-1; i>=0; i--) {
			byteBlock = Arrays.copyOfRange(allBytes, i * 1024, i * 1024 + 1024);
		    byte[] input = new byte[1024 + hashValue.length];
		    for (int j=0; j<1024; j++) {
		    	input[j] = byteBlock[j];
		    }
		    for (int j=0; j<hashValue.length; j++) {
		    	input[1024+j] = hashValue[j];
		    }
		    digest.update(input);
		    hashValue = digest.digest();
		}
		
		StringBuffer hexString = new StringBuffer();
		for (int i=0; i<hashValue.length; i++) {
			 String str = Integer.toHexString(0xFF & hashValue[i]);
			 if (str.length() == 1) {
				 str = "0" + str;
			 }
			 hexString.append(str);
		}
			
		System.out.println("Hex format : " + hexString.toString());
		
		in.close();
	}
}
