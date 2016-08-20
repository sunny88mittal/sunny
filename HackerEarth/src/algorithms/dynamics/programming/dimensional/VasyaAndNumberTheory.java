package algorithms.dynamics.programming.dimensional;

import java.util.Arrays;
import java.util.Scanner;

public class VasyaAndNumberTheory {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		scan.nextLine();
		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = scan.nextInt();
		}

		Arrays.sort(A);
		int dp[][] = new int[N][N];
		int max = -1;
		for (int i = N - 1; i >= 0; i--) {
			dp[i][i] = 1;
			for (int j = i + 1; j < N; j++) {
				dp[i][j] = dp[i][j - 1];
				if (A[j] % A[i] == 0) {
					dp[i][j] = Math.max(dp[i][j], dp[j][N - 1] + 1);
					max = max < dp[i][j] ? dp[i][j] : max;
				}
			}
		}
		System.out.println(max > 1 ? max : -1);
	}
}
