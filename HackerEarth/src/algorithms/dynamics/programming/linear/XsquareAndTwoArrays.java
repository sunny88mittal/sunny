package algorithms.dynamics.programming.linear;

import java.util.Scanner;

public class XsquareAndTwoArrays {

	private static long[] query1 = new long[100001];
	private static long[] query2 = new long[100001];

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		String tokens[] = scan.nextLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int Q = Integer.parseInt(tokens[0]);

		int A[] = new int[N];
		int B[] = new int[N];

		tokens = scan.nextLine().split(" ");
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(tokens[i]);
		}

		tokens = scan.nextLine().split(" ");
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(tokens[i]);
		}

		for (int i = 0; i < Q; i++) {
			tokens = scan.nextLine().split(" ");
			int type = Integer.parseInt(tokens[0]);
			int l = Integer.parseInt(tokens[1]);
			int r = Integer.parseInt(tokens[2]);

			if (type == 1) {
				System.out.println(solveQuery1(r, A, B) - solveQuery1(l - 1, A, B));
			} else {
				System.out.println(solveQuery2(r, A, B) - solveQuery2(l - 1, A, B));
			}
		}
	}

	private static long solveQuery1(int n, int[] A, int[] B) {
		if (query1[n] == 0) {
			if (n <= 0) {
				return 0;
			}

			if (n % 2 == 0) {
				query1[n] = B[n - 1] + solveQuery1(n - 1, A, B);
			} else {
				query1[n] = A[n - 1] + solveQuery1(n - 1, A, B);
			}
		}
		return query1[n];
	}

	private static long solveQuery2(int n, int[] A, int[] B) {
		if (query2[n] == 0) {
			if (n <= 0) {
				return 0;
			}

			if (n % 2 == 0) {
				query2[n] = A[n - 1] + solveQuery2(n - 1, A, B);
			} else {
				query2[n] = B[n - 1] + solveQuery2(n - 1, A, B);
			}
		}
		return query2[n];
	}
}