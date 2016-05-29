package week4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class CBCPaddingOracle {

	private static int[] knownBytes = new int[16];
	
	//Computing the bytes for the first block similarly we can compute for other blocks
	public static void main (String args[]) {
		String urlStrBase = "http://crypto-class.appspot.com/po?er=";
		for (int j=1; j<=16; j++) {
			System.out.println("Computing byte:" + j);
			int i;
			for (i=32; i<=122; i++) {
				String attackStr1 = "58b1ffb4210a580f748b4ac714c001bd"; 
				String attackStr2 =  "f20bdba6ff29eed7b046d1df9fb70000".substring(0, 32 - j*2);
				String attackStr3 =  getModifiedString("f20bdba6ff29eed7b046d1df9fb70000".substring(32 - j*2, 32), i);
				try {
					URL myURL = new URL(urlStrBase + attackStr2 + attackStr3 + attackStr1);
					BufferedReader in = new BufferedReader(new InputStreamReader(myURL.openStream()));
					String inputLine;
					while ((inputLine = in.readLine()) != null)
						System.out.println(inputLine);
					in.close();		
				} catch (FileNotFoundException fe) {
					knownBytes[j-1] = i;
					System.out.println((char)i);
					break;
				}  catch (IOException e) {
						
				}	
			}
		}
	}
	
	public static String getModifiedString(String str, int guess) {
		String returnStr = "";
		int padByte = str.length() / 2;
		int index = 0;
		for (int j=str.length()/2 -1; j>0; j--) {
			int cipherByte = Integer.parseInt(str.substring(j*2, j*2+2), 16);
			int actualByte = knownBytes[index];
			int modifiedByte = cipherByte ^ actualByte ^ padByte;
			index++;
			String modifiedByteStr = Integer.toHexString(modifiedByte);
			if (modifiedByteStr.length() < 2) {
				modifiedByteStr = "0" + modifiedByteStr;
			}
			returnStr =  modifiedByteStr + returnStr;
		}
		String str1 = Integer.toHexString(Integer.parseInt(str.substring(0, 2), 16) ^ guess ^ padByte);
		if (str1.length() < 0) {
			str1 = "0" + str1;
		}
		returnStr = str1 + returnStr;
		return returnStr;
	}
}
