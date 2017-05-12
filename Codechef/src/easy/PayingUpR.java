package easy;

import java.util.Scanner;

class PayingUpR {
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
			System.out.println(isPossible(nv, mo, nc - 1) ? "Yes" : "No");
		}
	}

	public static boolean isPossible(int[] nv, int mo, int endIndex) {
		if (mo == 0) {
			return true;
		}
		
		if (endIndex < 0 || mo < 0) {
			return false;
		}

		return isPossible(nv, mo, endIndex - 1) || isPossible(nv, mo - nv[endIndex], endIndex - 1);
	}
}
