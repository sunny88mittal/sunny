package SRM624;

import java.util.Arrays;

public class BuildingHeightsEasy {
	
	public int minimum(int M, int[] heights) {
		Arrays.sort(heights);
		
		int lowestRequired = Integer.MAX_VALUE;
		
		for (int i=0; i<heights.length; i++) {
			if (M + i > heights.length) {
				break;
			} 
			
			int max = heights[i];
			int sum = heights[i];
			
			for (int j=i+1; j<M+i; j++) {
				if (max < heights[j]) {
					max = heights[j];
				}
				sum += heights[j];
			}
			
			int newRequired  = max * M - sum;
			if (newRequired < lowestRequired) {
				lowestRequired = newRequired;
			}
		}
		
		return lowestRequired;
	}
}
