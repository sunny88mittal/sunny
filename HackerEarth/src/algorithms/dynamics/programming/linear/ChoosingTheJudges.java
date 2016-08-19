package algorithms.dynamics.programming.linear;

import java.util.Scanner;

public class ChoosingTheJudges {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int T = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(scan.nextLine());
			String tokens[] = scan.nextLine().split(" ");
			long dp[] = new long[N];
			for (int j = 0; j <= 1 && j < N; j++) {
				dp[j] = Integer.parseInt(tokens[j]);
				if (j - 1 >= 0) {
					if (dp[j] < dp[j - 1]) {
						dp[j] = dp[j - 1];
					}
				}
			}

			for (int j = 2; j < N; j++) {
				dp[j] = Integer.parseInt(tokens[j]) + dp[j - 2];
				if (dp[j] < dp[j - 1]) {
					dp[j] = dp[j - 1];
				}
			}

			System.out.println("Case " + (i+1) + ": " + dp[N - 1]);
		}
	}
}
