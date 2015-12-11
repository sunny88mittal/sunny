package introduction;

import java.math.BigDecimal;
import java.util.Scanner;

public class Exponentiation {

	public static void main(String args[]) throws Exception {
		Scanner cin = new Scanner(System.in);
		String str = cin.nextLine();
		while (str != null) {
			str = str.replaceAll("  ", " ");
			String tokens[] = str.split(" ");
			int exp = Integer.parseInt(tokens[1]);
			System.out.println(getExp(new BigDecimal(tokens[0]), exp).toPlainString());
			str = cin.nextLine();
		}
	}

	private static BigDecimal getExp(BigDecimal bg, int exp) {
		if (exp == 1) {
			return bg;
		}

		if (exp % 2 == 0) {
			return getExp(bg, exp / 2).multiply(getExp(bg, exp / 2));
		} else {
			return getExp(bg, exp / 2).multiply(getExp(bg, exp / 2)).multiply(bg);
		}
	}
}
