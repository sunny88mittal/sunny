package string;

import java.util.Arrays;
import java.util.Scanner;

public class Permutations {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			String str = sc.nextLine();
			char[] cstr = str.toCharArray();
			Arrays.sort(cstr);
			printPermutations(cstr, 0);
		}
	}

	private static void printPermutations(char str[], int pos) {
		if (pos == str.length) {
			System.out.print(new String(str) + " ");
		} else {
			for (int i = pos; i < str.length; i++) {
				char temp = str[pos];
				str[pos] = str[i];
				str[i] = temp;
				printPermutations(str, pos + 1);
				str[i] = str[pos];
				str[pos] = temp;
			}
		}
	}
}
