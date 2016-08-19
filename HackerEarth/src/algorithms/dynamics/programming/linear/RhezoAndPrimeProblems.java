package algorithms.dynamics.programming.linear;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RhezoAndPrimeProblems {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int N = Integer.parseInt(scan.nextLine());
		String tokens[] = scan.nextLine().split(" ");
		int[] points = new int[N + 1];
		int[] pre = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			points[i] = Integer.parseInt(tokens[i - 1]);
			pre[i] += pre[i - 1] + points[i];
		}

		// Prime's using sieve of eratosthenes
		List<Integer> primes = new ArrayList<Integer>();
		boolean prime[] = new boolean[N + 1];
		for (int i = 2; i <= Math.sqrt(N); i++) {
			int temp = i;
			if (!prime[temp]) {
				while (temp <= N) {
					prime[temp] = true;
					temp += i;
				}
			}
			prime[i] = false;
		}

		for (int i = 2; i <= N; i++) {
			if (!prime[i]) {
				primes.add(i);
			}
		}

		// Calculate the sum now
		int dp[] = new int[N + 1];
		dp[0] = 0;
		dp[1] = 0;
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1];
			for (int j = 0; j < primes.size() && primes.get(j) <= i; j++) {
				if (i == primes.get(j)) { // Take all the no.s till now
					dp[i] = Math.max(dp[i], pre[i]);
				} else {
					int p = i - primes.get(j) - 1;
					dp[i] = Math.max(dp[i], dp[p] + pre[i] - pre[p + 1]);
				}
			}
		}

		System.out.println(dp[N]);
	}
}