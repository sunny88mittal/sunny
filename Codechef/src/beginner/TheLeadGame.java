package beginner;

import java.util.Scanner;

class TheLeadGame {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int w = 0;
		int l = 0;
		int p1 = 0;
		int p2 = 0;
		for (int i = 0; i < n; i++) {
			p1 += sc.nextInt();
			p2 += sc.nextInt();
			if (p1 > p2) {
				if (p1 - p2 > l) {
					l = p1 - p2;
					w = 1;
				}
			} else {
				if (p2 - p1 > l) {
					l = p2 - p1;
					w = 2;
				}
			}
		}
		System.out.println(w + " " + l);
	}
}
