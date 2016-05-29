package introduction;

import java.util.Scanner;

public class RideToSchool {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		while (scan.hasNext()) {
			int n = scan.nextInt();
			if (n == 0)
				break;
			double min = 0x7fffffff, time;
			while (n-- > 0) {
				int v = scan.nextInt();
				int t = scan.nextInt();
				if (t < 0)
					continue;
				if ((time = 4.5 / v * 3600 + t) < min)
					min = time;
			}
			System.out.println((int) Math.ceil(min));
		}
	}
}