package algorithms.dynamics.programming.linear;

import java.util.Scanner;

public class Staircase {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int T = Integer.parseInt(scan.nextLine());
		if (T <= 0) {
			System.out.println("ERROR");
			return;
		}
		System.out.println("Jack-" + getCountForJack(T));
		System.out.println("Jill-" + getCountForJill(T));
	}

	private static int getCountForJack(int n) {
		int dp[] = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int i = 4; i <= n; i++) {
			dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
		}
		return dp[n];
	}

	private static int getCountForJill(int n) {
		int dp[] = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 2] + dp[i - 1];
		}
		return dp[n];
	}
}
