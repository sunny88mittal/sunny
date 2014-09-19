package SRM148;

import java.util.HashMap;
import java.util.Map;

public class CeyKaps {
	
	public String decipher(String typed, String[] switches) {
		String finalString = "";
		Map<Character, Integer> charPosMap = new HashMap<Character, Integer>();
		int j=0;
		for (char i='A'; i<='Z'; i++) {
			charPosMap.put(i, j);
			j++;
		}
		
		for (String sswitch : switches) {
			String[] characters = sswitch.split(":");
			char charA = characters[0].charAt(0);
			char charB = characters[1].charAt(0);
			 
			int posA = charPosMap.get(charA);
			int posB = charPosMap.get(charB);
			
			charPosMap.put(charA, posB);
			charPosMap.put(charB, posA);
		}
		
		char[] finalChars = new char[26];
		
		for (Map.Entry<Character, Integer> entry : charPosMap.entrySet()) {
			finalChars[entry.getValue()] = entry.getKey();
		}
		
		char typedChar[] = typed.toCharArray();
		for (int i=0; i<typedChar.length; i++){
			int position = typedChar[i] - 'A';
			finalString += finalChars[position]; 
		}
		
		return finalString;
	}

}
