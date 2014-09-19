package SRM621;

//For the solution explantation see http://apps.topcoder.com/wiki/display/tc/SRM+621
public class MixingColors {

	public int minColors(int[] colors) {
		int j = 0;
		int n = colors.length;
		for (int i = 0; i < 31; i++) { // We will use this loop to check for the bit at position i in colors element
			int k = j;
			while (k < n && (colors[k] & 1 << i) == 0) { //Ignore this many numbers as they don't have the bit i set
				k++;
			}
			
			if (k!= n) {  //Swap k and j element, Take k to the top of array in consideration
				int temp = colors[k];
				colors[k] = colors[j];
				colors[j] = temp;
				
				for (int m=j+1; m<n; m++) { //Clear the bit the i for the remaining elements in the array       
					if ((colors[m] & 1 << i)!= 0) {
						colors[m] = colors[m] ^ colors[j];
					}
				}
				j++;
			}
		}
		
		int count = 0;
		while (count < n && colors[count] !=0) {
			count++;
		}

		return count;
	}
}
