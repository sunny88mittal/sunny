package July122016;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LittleShinoAndCoins {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int k = scan.nextInt();
		String str = scan.next();
		int output = 0;

		Set<Character> charSet = new HashSet<Character>();
		for (int i = 0; i < str.length(); i++) {
			charSet.clear();
			for (int j = i; j < str.length(); j++) {
	            charSet.add(str.charAt(j));
	            if (charSet.size() == k) {
	            	++output;
	            } else if (charSet.size() > k) {
	            	break;
	            }
			}   
		}
		
		System.out.println(output);
	}
}
