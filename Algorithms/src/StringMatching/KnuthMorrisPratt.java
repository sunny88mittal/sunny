package StringMatching;

public class KnuthMorrisPratt {
	
	public static void main (String args[]) {
		//String txt =  "AABAACAABAA";
		String txt =  "AABAACAADAABAAABAA";
		String pat = "ababa";
		int[] lps = createLPS(pat);
	    
		int j = 0;
		int i = 0;
		while (i < txt.length()) {
			if (txt.charAt(i) == pat.charAt(j)) {   //char matches so continue
				i++;
				j++;
				if (j == pat.length()) {
					System.out.println("Pattern found at:" + (i-j));
					j = 0;
				}
				continue;
			}
			
			if (j!=0) { // Does not matches slide the pattern
				j = lps[j-1];
			} else { //Mismatch at first character move to next character in text
				i++;
			}
		}
	}
	
	
	private static int[] createLPS(String patt) {
		int length = patt.length();
		int[] lps = new int[length];
		lps [0] = 0;
		int i = 1;
		int len = 0;
		while (i <length) {
		    if (patt.charAt(i) == patt.charAt(len)) { // Match
		    	len++;                             
		    	lps[i] = len;
		    	i++;
		    } else {
		    	if (len > 0) {
		    		len = lps[len -1];   // Try with a lower size prefix
		    	} else {
		    		lps[i] = 0;  //No prefix available
		    		i++;
		    	}
		    }
		}
		return lps;
	}
}
