package basics.bitmanipulation;

import java.util.Scanner;

public class TheMagic {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int n = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < n; i++) {
			int N = Integer.parseInt(scan.nextLine());
			int count = 0;
			while (N != 0) {
				if (N % 2 == 1) {
					count++;
				}
				N >>= 1;
			}
			System.out.println(count);
		}
	}
}
