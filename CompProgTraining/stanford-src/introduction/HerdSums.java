package introduction;

import java.util.Scanner;

public class HerdSums {
	public static void main(String args[]) throws Exception {
		Scanner cin = new Scanner(System.in);
		int n = cin.nextInt();
		int count = 1; 
		for (int i=1; i<=n/2; i++) {
			int temp = i;
			int sum = 0;
			while (sum < n) {
				sum += temp;
				temp++;
			}
			if (sum == n) {
				count++;
			}
		}
		System.out.println(count);
	}
}
