package algorithms.dynamics.programming.dimensional;

import java.util.Scanner;

public class royndflower {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int t = s.nextInt();
		while (t-- > 0) {
			int n = s.nextInt();
			int p = s.nextInt();

			long knap[][] = new long[105][10006];
			int val[] = new int[n];
			int wt[] = new int[n];
			for (int i = 0; i < n; i++) {
				int x = s.nextInt();
				int y = s.nextInt();
				val[i] = x - y;
				wt[i] = y;
			}
			for (int i = 0; i <= n; i++) {
				for (int j = 0; j <= p; j++) {
					if (i == 0 || j == 0)
						knap[i][j] = 0;
					else if (val[i - 1] < 0)
						knap[i][j] = knap[i - 1][j];
					else if (wt[i - 1] <= j) {
						knap[i][j] = Math.max(knap[i - 1][j - wt[i - 1]] + val[i - 1], knap[i - 1][j]);
					} else
						knap[i][j] = knap[i - 1][j];

				}
			}
		    long ans = knap[n][p];
			boolean an = false;
			for (int i = p; i >= 0; i--) {
				if (knap[n][i] != ans) {
					System.out.println((i + 1) + " " + (ans + p));
					an = true;
					break;
				}
			}
			if (an == false) {
				System.out.println("0 " + p);
			}
		}
	}
}