package beginner;

import java.util.*;

class ATM {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		float bal = sc.nextFloat();
		if (x % 5 == 0 && x <= bal - .50) {
			bal = bal - x - (float) 0.50;
		}
		System.out.println(bal);
	}
}