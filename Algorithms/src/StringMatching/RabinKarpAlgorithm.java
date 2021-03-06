package StringMatching;

import java.math.BigInteger;
import java.util.Random;

public class RabinKarpAlgorithm {

	private static int d = 256;

	private static int q = BigInteger.probablePrime(12, new Random()).intValue();

	public static void main(String args[]) {
		String pattern = "ABABAC";
		String text = "ABABACAABCABABAC";
		findMatches(pattern, text);
	}

	/**
	 * Finds the pattern using the Rabin Karp Algorithm
	 * 
	 * @param pattern
	 * @param text
	 */
	private static void findMatches(String pattern, String text) {
		char[] pc = pattern.toCharArray();
		char[] tc = text.toCharArray();

		int pMod = 0;
		int tMod = 0;
		int pl = pattern.length();

		// Calculate d^(pl-1)%q
		int exp = 1;
		for (int i = 0; i < pl - 1; i++) {
			exp = (exp * d) % q;
		}

		// Calculate initial values
		for (int i = 0; i < pl; i++) {
			pMod = (pMod * d + pc[i]) % q;
			tMod = (tMod * d + tc[i]) % q;
		}

		// Check the pattern in the text
		for (int s = 0; s <= text.length() - pl; s++) {
			// Validate if the match is correct
			if (pMod == tMod) {
				if (pattern.equals(text.substring(s, s + pl))) {
					System.out.println("Match found at :" + s);
				}
			}

			if (s < text.length() - pl) {
				tMod = (tMod + q - exp * tc[s] % q) % q; // Remove first character
				tMod = (tMod * d) % q; // Raise all characters power by 1
				tMod = (tMod + tc[pl + s]) % q; // Add the last character
			}
		}
	}
}