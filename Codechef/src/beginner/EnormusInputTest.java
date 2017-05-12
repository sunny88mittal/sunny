package beginner;

import java.util.*;

class EnormusInputTest {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int c = 0;
		for (int i = 0; i < n; i++) {
			if (sc.nextInt() % k == 0) {
				++c;
			}
		}
		System.out.println(c);
	}
}
