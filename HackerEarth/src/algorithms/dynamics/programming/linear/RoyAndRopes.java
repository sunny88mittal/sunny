package algorithms.dynamics.programming.linear;

import java.util.Scanner;

public class RoyAndRopes {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int T = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < T; i++) {
			int L = Integer.parseInt(scan.nextLine());
			int upper;
			int lower;

			String[] tokens = scan.nextLine().split(" ");
			String[] tokens1 = scan.nextLine().split(" ");

			int residual = 0;
			for (int j = 2; j <= L; j++) {
				upper = Integer.parseInt(tokens[j - 2]);
				lower = Integer.parseInt(tokens1[j - 2]);
				--residual;
				int max = upper > lower ? upper : lower;
				if (max > residual) {
					residual = max;
				}
			}

			if (residual > 0) {
				--residual;
			} else {
				residual = 0;
			}
			System.out.println(L + residual);
		}
	}
}