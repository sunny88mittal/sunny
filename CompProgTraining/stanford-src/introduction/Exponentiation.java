package introduction;

import java.math.BigDecimal;
import java.util.Scanner;

public class Exponentiation {
	public static void main(String args[]) {
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext()) {
			BigDecimal b = cin.nextBigDecimal();
			int n = cin.nextInt();
			BigDecimal a = BigDecimal.valueOf(1);
			for (int i = 1; i <= n; i++)
				a = a.multiply(b);
			a = a.stripTrailingZeros();
			String pre = a.toPlainString();
			if (pre.startsWith("0."))
				pre = pre.substring(1);
			System.out.println(pre);
		}
	}
}