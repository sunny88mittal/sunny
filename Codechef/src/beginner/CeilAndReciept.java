package beginner;

import java.util.Scanner;

class CeilAndReciept {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			int n = sc.nextInt();
			int sum = 0;
			int p = 2048;
			while (n > 0) {
				sum += n / p;
				n = n % p;
				p = p >> 1;
			}
			System.out.println(sum);
		}
	}
}
