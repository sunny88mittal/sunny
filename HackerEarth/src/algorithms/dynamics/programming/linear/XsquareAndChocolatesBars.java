package algorithms.dynamics.programming.linear;

import java.util.Scanner;

public class XsquareAndChocolatesBars {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int T = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < T; i++) {
			char[] chocolate = scan.nextLine().toCharArray();
			int length = chocolate.length;
			int ans = 0;
			int[] dp = new int[length];
			boolean same = chocolate[0] == chocolate[1];
			for (int j = 2; j < length; j++) {
				if (same && chocolate[j] == chocolate[j - 1]) {
					// Do nothing
				} else {
					dp[j] = 3 + (j >= 3 ? dp[j - 3] : 0);
				}
				ans = Math.max(ans, dp[j]);
				same = chocolate[j] == chocolate[j - 1];
			}
			System.out.println(length - ans);
		}
	}
}
