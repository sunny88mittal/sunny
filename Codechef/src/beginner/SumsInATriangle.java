package beginner;

import java.util.Scanner;

class SumsInATriangle {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			int l = sc.nextInt();
			int[][] arr = new int[l][l];
			for (int j = 0; j < l; j++) {
				for (int k = 0; k <= j; k++) {
					arr[j][k] = sc.nextInt();
				}
			}

			for (int m = l - 2; m >= 0; m--) {
				for (int n = 0; n <= m; n++) {
					arr[m][n] = Math.max(arr[m][n] + arr[m + 1][n], arr[m][n] + arr[m + 1][n + 1]);
				}
			}

			System.out.println(arr[0][0]);
		}
	}
}
