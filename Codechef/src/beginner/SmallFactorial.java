package beginner;

import java.math.*;
import java.util.*;

class SmallFactorial {

	public static void main(String args[]) {
		BigInteger[] bg = new BigInteger[101];
		bg[0] = new BigInteger("1");
		for (int i = 1; i <= 100; i++) {
			bg[i] = bg[i - 1].multiply(BigInteger.valueOf(i));
		}
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			int n = sc.nextInt();
			System.out.println(bg[n]);
		}
	}
}
