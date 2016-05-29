package DynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
 * 
   Optimal Substructure:  Suppose the strings are X[m] and Y[n].
            >For characters at X[i] and Y[j] if they match they get included for the LCS[i][j]. Now we need to append the result of the 
             LCS[i-1][j-1] so for the match case result will be  LCS[i][j] = LCS[i-1][j-1] + 1
            >In case of mismatch for X[i] and Y[j] we have to try two cases
             LCS[i][j-1] and LCS[i-1][j] and take the best of these two                   
		
   LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
   LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
 * @author sumittal
 *
 */
public class LongestCommonSubsequence {
	
	public static void main (String args[]) {
		String X = "AGGTAB";
		String Y = "GXTXAYB";
		
		char[] cX = X.toCharArray();
		char[] cY = Y.toCharArray();
		
		int xLength = cX.length;
		int yLength = cY.length;
		
		int lcs[][] = new int[xLength +1][yLength +1];
		
		//Compute the LCS
		for (int i=0; i<=xLength; i++) {
			for (int j=0; j<=yLength; j++) {
				if (i == 0 || j ==0) {
					lcs[i][j] = 0; //No LCS possible with the other string having the size 0
				} else if (cX[i-1] == cY[j-1]) { //match case
					lcs[i][j] = lcs[i-1][j-1] + 1;
				} else { // no match case
					lcs[i][j] = max (lcs[i-1][j], lcs[i][j-1]);
				}
			}
		}
		
        //Print the LCS
		System.out.println("Max LCS is:" + lcs[xLength][yLength]);
		List<Character> lcsChrac = new ArrayList<Character>();
		int i = xLength; 
		int j = yLength;
		while (i >0 && j>0) {
			 if (cX[i-1] == cY[j-1]) {
				 lcsChrac.add(cX[i-1]); // Put current character in result
		         i--; 
		         j--; 
		      } else if (lcs[i-1][j] > lcs[i][j-1]) // If not same, then find the larger of two an                              
		    	 i--;                                   // go in the direction of larger value
		      else
		         j--;
		}
		Collections.reverse(lcsChrac);
		System.out.println("LCS is:" + lcsChrac);
	}
	
	private static int max (int a, int b) {
		return (a > b) ? a : b;
	}
}
