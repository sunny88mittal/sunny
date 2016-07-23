package datastructures.array;

import java.util.Scanner;

public class BiasedChandan {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int n = Integer.parseInt(scan.nextLine());
		int scores[] = new int[n];
		for (int i = 0; i < n; i++) {
			scores[i] = Integer.parseInt(scan.nextLine());
		}

		int sum = 0;
		int count = 0;
		for (int i = n - 1; i >= 0; i--) {
            if (scores[i] == 0) {
            	count ++;
            } else if (count > 0) {
            	count --;
            } else {
            	sum += scores[i];
            }
		}

		System.out.println(sum);
	}
}
