package beginner;

import java.util.*;

class Factorial {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			int sum = 0;
			while (a >= 5) {
				sum += a / 5;
				a = a / 5;
			}
			System.out.println(sum);
		}
	}
}
