package introduction;

import java.util.Scanner;

public class AddingReversedNumbers {

	public static void main(String args[]) throws Exception {
		Scanner cin = new Scanner(System.in);
		int N = cin.nextInt();
		for (int i=0; i<N; i++) {
			int n1 = cin.nextInt();
			int n2 = cin.nextInt();
			n1 = reverse(n1);
			n2 = reverse(n2);
			long sum = (long)n1 + (long) n2;
			while (sum % 10  == 0) {
				sum = sum /10;
			}
			System.out.println(reverse(sum));	
		}
	}
	
	private static int reverse(int n) {
		int rn = 0;
		while (n / 10 != 0) {
			rn = (rn + n % 10) * 10;
			n = n/10;
		}
		rn = rn + n;
		return rn;
	}
	
	private static long reverse(long n) {
		long rn = 0;
		while (n / 10 != 0) {
			rn = (rn + n % 10) * 10;
			n = n/10;
		}
		rn = rn + n;
		return rn;
	}
}
