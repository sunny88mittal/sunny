package SRM672;

import java.util.HashMap;
import java.util.Map;

public class SubstitutionCipher {

	public String decode(String a, String b, String y) {
		Map<Character, Character> map = new HashMap<Character, Character>();
		for (int i=0; i<b.length(); i++) {
			map.put(b.charAt(i),a.charAt(i));
		}
		
		if (map.size() == 25) {
			char cchar = 'A';
			char ccipher = 'A';
			for (char c = 'A'; c<='Z'; c++) {
				if (!map.keySet().contains(c)) {
					ccipher = c;
					break;
				}
			}
			
			for (char c = 'A'; c<='Z'; c++) {
				if (!map.values().contains(c)) {
					cchar = c;
					break;
				}
			}
			
			map.put(ccipher, cchar);
		}
		
		String result = "";
		for (int i=0; i<y.length(); i++) {
			if (map.get(y.charAt(i)) != null) {
				result += map.get(y.charAt(i));
			} else {
				return "";
			}
		}
		
		return result;
	}
	
	public static void main (String args[]) {
		SubstitutionCipher obj = new SubstitutionCipher();
		System.out.println(obj.decode("CAT", "DOG", "GOD"));
		System.out.println(obj.decode("BANANA", "METETE", "TEMP"));
		System.out.println(obj.decode("THEQUICKBROWNFOXJUMPSOVERTHELAZYHOG", "UIFRVJDLCSPXOGPYKVNQTPWFSUIFMBAZIPH", "DIDYOUNOTICESKIPPEDLETTER"));
	}
}
