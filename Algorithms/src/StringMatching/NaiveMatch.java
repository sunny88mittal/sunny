package StringMatching;

/**
 * Naive Pattern Matching
 * @author sumittal
 * Time Complexity Worst Case O((n-m+1)m)
 */

public class NaiveMatch {

	public static void main (String args[]) {
		String pattern = "0001";
		String text = "000010001010001";
		
		int patternLength = pattern.length();
		int textLength = text.length();
		
		int toCompareTill = textLength - patternLength;
		
		for (int i=0; i<=toCompareTill; i++) {
			boolean broke = false;
			for (int j=0; j<patternLength; j++) {
				if (text.charAt(i+j) != pattern.charAt(j)) {
					broke = true;
					break;
				}
			}
			if (!broke) {
				System.out.println("Match at:" + i);
			}
		}
	}
}
