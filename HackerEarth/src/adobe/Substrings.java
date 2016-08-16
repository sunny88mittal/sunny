package adobe;

import java.util.ArrayList;
import java.util.List;

public class Substrings {

	public static void main(String[] args) {
		printSubstring("aasdkmdhuewndscunskdwqdwqewqhdnsfs");
		printSubstring("abc");
		printSubstring("ab");
		printSubstring("aba");
	}

	private static void printSubstring(String s) {
		System.out.println();
		List<Character> vowels = new ArrayList<>();
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');

		int len = s.length(); // Index of last consonant
		while (vowels.contains(s.charAt(len - 1))) {
			len--;
		}

		int start = 0; // Index of first vowel
		while (!vowels.contains(s.charAt(start))) {
			start++;
		}

		String first = "" + s.charAt(start);
		String last = "" + s.charAt(start);
		++start;

		int temp = start;
		boolean done = false;
		while (temp < len) {
			char ch = s.charAt(temp);
			if (vowels.contains(ch)) {
				if (ch <= first.charAt(0)) {
					first = "" + ch;
					done = false;
				}
			} else {
				if (!done) {
					first += ch;
					done = true;	
				}
				while (temp < len && !vowels.contains(s.charAt(temp))) {
					temp++;
				}
			}
			++temp;
		}

		temp = start;
		while (temp < len) {
			char ch = s.charAt(temp);
			if (vowels.contains(ch)) {
				if (ch > last.charAt(0)) {
					last = "" + ch;
				}
			} else {
				last += ch;
			}
			++temp;
		}

		System.out.println(first);
		System.out.println(last);
	}
}
