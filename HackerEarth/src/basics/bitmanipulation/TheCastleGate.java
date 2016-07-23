package basics.bitmanipulation;

import java.util.Scanner;

public class TheCastleGate {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int n = Integer.parseInt(scan.nextLine());
		for (int i=0; i<n; i++) {
			int N = Integer.parseInt(scan.nextLine());
			int count = 0;
			for (int j=1; j<=N; j++) {
				for (int k=j+1; k<=N; k++) {
					if ((j ^ k) <= N) {
						count++;
					}
				}
			}
			System.out.println(count);
		}
	}
}
