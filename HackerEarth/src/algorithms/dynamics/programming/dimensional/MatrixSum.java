package algorithms.dynamics.programming.dimensional;

import java.util.Scanner;

public class MatrixSum {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int M = scan.nextInt();
		int N = scan.nextInt();

		int[][] mat = new int[M][N];
		for (int i = 0; i < M; i++) {
			scan.nextLine();
			for (int j = 0; j < M; j++) {
				mat[i][j] = scan.nextInt();
			}
		}

		int Q = scan.nextInt();
		for (int i = 0; i < Q; i++) {
			scan.nextLine();
			int m = scan.nextInt();
			int n = scan.nextInt();
		}
		
		
	}
}
