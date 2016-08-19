package algorithms.dynamics.programming.linear;

import java.util.Scanner;

public class OnceUponATimeInTimeLand {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int T = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < T; i++) {
			String tokens[] = scan.nextLine().split(" ");
			int N = Integer.parseInt(tokens[0]);
			int K = Integer.parseInt(tokens[1]);

			long dp[] = new long[N];
			long ans = 0;
			tokens = scan.nextLine().split(" ");
			long value = Integer.parseInt(tokens[0]);
			dp[0] = value > 0 ? value : 0;

			for (int j = 1; j < N; j++) {
				value = Integer.parseInt(tokens[j]);
				if (value > 0) { // No should be + ve
					if (j - K - 1 >= 0) { // Take from A[j-1]
						dp[j] = dp[j - K - 1];
					}
					dp[j] += value; // Add the current element
				}
				if (dp[j] < dp[j - 1]) { // If less than previous use that
					dp[j] = dp[j - 1];
				}
				ans = Math.max(ans, dp[j]);
			}

			System.out.println(ans);
		}
	}
}