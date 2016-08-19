package algorithms.dynamics.programming.linear;

import java.util.Scanner;

public class XsquareAndTwoArrays {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		String tokens[] = scan.nextLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int Q = Integer.parseInt(tokens[0]);

		long A[] = new long[N];
		long B[] = new long[N];

		tokens = scan.nextLine().split(" ");
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(tokens[i]);
		}

		tokens = scan.nextLine().split(" ");
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(tokens[i]);
		}

		long[] query1 = new long[N + 1];
		long[] query2 = new long[N + 1];

		for (int i = 1; i <= N; i++) {
			if (i % 2 == 0) {
				query1[i] = query1[i - 1] + B[i - 1];
				query2[i] = query2[i - 1] + A[i - 1];
			} else {
				query1[i] = query1[i - 1] + A[i - 1];
				query2[i] = query2[i - 1] + B[i - 1];
			}
		}

		for (int i = 0; i < Q; i++) {
			tokens = scan.nextLine().split(" ");
			int type = Integer.parseInt(tokens[0]);
			int l = Integer.parseInt(tokens[1]);
			int r = Integer.parseInt(tokens[2]);

			if (type == 1) {
				System.out.println(query1[r] - query1[l - 1]);
			} else {
				System.out.println(query2[r] - query2[l - 1]);
			}
		}
	}
}