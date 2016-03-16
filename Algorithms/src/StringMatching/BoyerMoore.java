package StringMatching;

import java.util.ArrayList;
import java.util.List;

public class BoyerMoore {

	private static int R = 256;

	public static void main(String args[]) {
		String text = "FINDNEEDLEINNEEDLEHAYNEEDSNEEDLS";
		String pattern = "NEEDLE";
		int[] skipTable = getSkipTable(pattern);
		List<Integer> matches = getMatches(text, pattern, skipTable);
		for (Integer i : matches) {
			System.out.println("Match found at: " + i);
		}
	}

	/**
	 * Gives the right most occurrence for a char in pattern
	 * 
	 * @param pattern
	 * @return skip table
	 */
	private static int[] getSkipTable(String pattern) {
		int[] skipTable = new int[R];
		char[] cpattern = pattern.toCharArray();
		for (int i = 0; i < R; i++) {
			skipTable[i] = -1;
		}
		for (int i = 0; i < cpattern.length; i++) {
			skipTable[cpattern[i]] = i;
		}
		return skipTable;
	}

	private static List<Integer> getMatches(String text, String pattern, int[] skipTable) {
		List<Integer> matchList = new ArrayList<Integer>();
		char[] textc = text.toCharArray();
		char[] patternc = pattern.toCharArray();
		int m = textc.length;
		int n = patternc.length;
		int skip = 0;

		for (int i = 0; i <= m - n; i += skip) {
			skip = 0;
			for (int j = n - 1; j >= 0; j--) {
				if (patternc[j] != textc[i + j]) {
					skip = Math.max(1, j - skipTable[textc[i + j]]);
					break;
				}
			}
			if (skip == 0) {
				matchList.add(i);
				skip = 1; //Move 1 char next to search for next pattern
			}
		}
		return matchList;
	}
}
