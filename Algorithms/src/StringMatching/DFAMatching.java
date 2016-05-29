package StringMatching;

/**
 * KMP Algorithm
 * @author sumittal
 * Time Complexity : O(RM) +  O(N)
 *    R --> No of characters possible
 *    M --> Pattern Length
 *    N --> Text Length
 * Space Complexity : O(RM)
 *    
 */
public class DFAMatching {
	
	private static int charsPossible = 256;
	
	public static void main (String args[]) {
		String pattern = "ABABAC";
		String text = "AAAABCABABAC";
		System.out.println(DFAMatch(text, pattern));
	}
	
	/**
	 * Matches the string according to the KMP pattern
	 * @param text
	 * @param pattern
	 * @return
	 */
	private static int DFAMatch(String text, String pattern) {
	   int match = -1;	
	   int textLength = text.length();
	   int patternLength = pattern.length();
	   int dfa[][] = createDFA(pattern);
	   
	   int i,j;
	   for (i=0,j=0; i<textLength && j<patternLength; i++)
	       j = dfa[text.charAt(i)][j];
	   
	   if (j == patternLength)
		   match = i -patternLength;
	   
	   return match;
	}
	
	/**
	 * Creates DFA for the pattern String
	 * Algorithm to create DFA
	 *    1. Match Case
	 *       If in state j and next char c = pat.charAt(j), goto j+1 
	 *    2. Mismatch Case
	 *       For each state j and char c!= pat.charAt(j) set dfa[c][j] = dfa[c][x]
	 *       Than update x = dfa[pat.charAt(j)][x]
	 *    With each update k keeps on storing the state we will reach 
	 * @param pattern
	 * @return
	 */
	private static int[][] createDFA(String pattern) {
		int[][] dfa;
		int patternLength = pattern.length();
		dfa = new int[charsPossible][patternLength];
		int k = 0;
		dfa[pattern.charAt(0)][0] = 1;
		
		for (int i=1; i<patternLength; i++) {
		    for (int j=0; j<charsPossible; j++)
		    	dfa[j][i] = dfa[j][k];        //Mismatch Case
			dfa[pattern.charAt(i)][i] = i+1;  //Match Case
			k = dfa[pattern.charAt(i)][k];    //Update
		}
		
		return dfa;
	}

}
