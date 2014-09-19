package DynamicProgramming;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-6-min-cost-path/
 * @author sumittal
 */

public class MinCostPath {

	public static void main (String args[]) {
		int [][] pathArray = new int[][] { {1, 2, 3},
                                           {4, 8, 2},
                                           {1, 5, 3} 
                                         };
		int rows = pathArray.length;
		int cols = pathArray[0].length;
		
		int[][] minPathArray = new int[rows][cols];
		
		
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				int tempDistance = Integer.MAX_VALUE;
				//right
				if (j-1 >= 0) {
					if (pathArray[i][j] + minPathArray[i][j-1] < tempDistance) {
						tempDistance = pathArray[i][j] +  minPathArray[i][j-1];	
					}
				}	
				//down
				if (i-1 >=0) {
					if (pathArray[i][j] + minPathArray[i-1][j] < tempDistance) {
						tempDistance = pathArray[i][j] + minPathArray[i-1][j];	
					}
				}
				//diagonal
				if (j-1 >= 0 && i-1 >=0) {
					if (pathArray[i][j] + minPathArray[i-1][j-1] < tempDistance) {
						tempDistance = pathArray[i][j]+  minPathArray[i-1][j-1];	
					}
				}
				
				if (tempDistance == Integer.MAX_VALUE) {
					minPathArray[i][j] = pathArray[i][j];	
				} else {
					minPathArray[i][j] = tempDistance;
				}
			}
			
		}
		
		System.out.println("Min diatnce is:" + minPathArray[rows-1][cols-1]);
	}
}
