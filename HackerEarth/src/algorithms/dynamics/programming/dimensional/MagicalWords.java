package algorithms.dynamics.programming.dimensional;

import java.util.Scanner;

public class MagicalWords {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		scan.nextLine();
		for (int i = 0; i < T; i++) {
			String str = scan.nextLine();
			System.out.println(getSubPalidromesCount(str));
		}
	}

	private static long getSubPalidromesCount(String str) {
		int length = str.length();
		long sum = length;
		boolean[][] isPalindrome = new boolean[length][length];

		for (int i = length - 1; i >= 0; i--) {
			isPalindrome[i][i] = true;
			for (int j = i + 1; j < length; j++) {
				boolean palindrome = str.charAt(i) == str.charAt(j);
				if (j - i > 1) {
					palindrome = palindrome && isPalindrome[i + 1][j - 1];
				}
				if (palindrome) {
					sum += (j - i + 1) * (j - i + 1);
				}
				isPalindrome[i][j] = palindrome;
			}
		}

		return sum;
	}
}
