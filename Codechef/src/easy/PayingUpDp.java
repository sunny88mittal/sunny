package easy;

import java.util.Scanner;

class PayingUpDp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			int nc = sc.nextInt();
			int mo = sc.nextInt();
			int nv[] = new int[nc];
			for (int j = 0; j < nc; j++) {
				nv[j] = sc.nextInt();
			}

			boolean[][] possible = new boolean[nc + 1][mo + 1];
			for (int j = 0; j <= nc; j++) { // 0 some possible for all
				possible[j][0] = true;
			}

			for (int j = 1; j <= nc; j++) {
				for (int k = 1; k <= mo; k++) {
					if (nv[j - 1] <= k) {
						possible[j][k] = possible[j - 1][k - nv[j - 1]] || possible[j - 1][k];
					} else {
						possible[j][k] = possible[j - 1][k];
					}
				}
			}

			System.out.println(possible[nc][mo] ? "Yes" : "No");
		}
	}
}
