package datastructures.array;

import java.util.Scanner;

public class ModifySequence {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.nextLine();
		String[] tokens = scan.nextLine().split(" ");
		int[] numbs = new int[n];
		for (int i = 0; i < n; i++) {
			numbs[i] = Integer.parseInt(tokens[i].trim());
		}

		for (int i = 0; i < n - 1; i++) {
			if (numbs[i] <= numbs[i + 1]) {
				numbs[i + 1] = numbs[i + 1] - numbs[i];
			} else {
				System.out.println("NO");
				return;
			}
		}

		if (numbs[n - 1] != 0) {
			System.out.println("NO");
			return;
		}

		System.out.println("YES");
	}
}
