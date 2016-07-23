package array;

import java.util.Scanner;

public class MarkTheAnswer {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		String tokens[] = scan.nextLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int D = Integer.parseInt(tokens[1]);
		int diff[] = new int[N];
		tokens = scan.nextLine().split(" ");
		for (int i = 0; i < N; i++) {
			diff[i] = Integer.parseInt(tokens[i].trim());
		}

		boolean skipped = false;
		int count = 0;
		for (int i = 0; i < N; i++) {
			if (diff[i] <= D) {
				count++;
			} else {
				if (skipped) {
					break;
				} else {
					skipped = true;
				}
			}
		}

		System.out.println(count);
	}
}