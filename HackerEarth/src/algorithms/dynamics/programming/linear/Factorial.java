package algorithms.dynamics.programming.linear;

import java.util.Scanner;

public class Factorial {

	private static long[] factorials = new long[100000];

	static {
		factorials[0] = 1;
	}

	private static long modulus = 1000000007;

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int N = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < N; i++) {
			int m = Integer.parseInt(scan.nextLine());
			System.out.println(getFactorial(m));
		}
	}

	private static long getFactorial(long m) {
		if (factorials[(int) m] != 0) {
			return factorials[(int) m];
		}
		factorials[(int) m] = ((long) m * getFactorial(m - 1)) % modulus;
		return factorials[(int) m];
	}
}
