package sap;

import java.util.Scanner;

public class MillyAndSubArrays {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int n = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < n; i++) {
			int m = Integer.parseInt(scan.nextLine());
			int values[] = new int[m];
			String[] tokens = scan.nextLine().split(" ");
			for (int j = 0; j < m; j++) {
				values[j] = Integer.parseInt(tokens[j]);
			}

			long tcount = 0;
			long count = 1;
			int temp = values[0];
			for (int j = 1; j < m; j++) {
				if (values[j] == temp) {
					count++;
				} else {
					temp = values[j];
					tcount += (count * (count + 1)) / 2;
					count = 1;
				}
			}
			tcount += (count * (count + 1)) / 2;

			System.out.println(tcount);
		}
	}
}
