package SRM621;

public class NumbersChallenge {

	public int MinNumber(int[] S) {
		boolean possibleSums[] = new boolean[20*100000+1];
		
		int toalSubsets = 1<<S.length;   //This will be total no. of subsets 2^n
		
		for (int i=0; i<toalSubsets; i++) { //Each bit set in the i gives us the element which we should include in the subset
			int sum = 0;
			for (int j=0; j<S.length; j++) { //This loop will check which bit is set in the subset indicating string and add those elements
				if ((i & (1<<j)) !=0 ) {
					sum += S[j];
				}
			}
			possibleSums[sum] = true;
		}
		
		int x = 1;
		while (possibleSums[x]) {
			x++;
		}
		
		return x;
	}
}
