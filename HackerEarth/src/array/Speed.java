package array;

import java.util.Scanner;

public class Speed {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.nextLine();
		for (int i = 0; i < n; i++) {
			int m = Integer.parseInt(scan.nextLine());
			long speeds[] = new long[m];
			String[] tokens = scan.nextLine().split(" ");
			for (int j = 0; j < m; j++) {
				speeds[j] = Long.parseLong(tokens[j].trim());
			}

			int count = 1;
			long minFrontSpeed = speeds[0];
			for (int j = 1; j < m; j++) {
				if (speeds[j] < minFrontSpeed) {
					minFrontSpeed = speeds[j];
					count++;
				}
			}

			System.out.println(count);
		}
	}
}
