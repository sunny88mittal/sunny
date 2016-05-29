package com.sunny.array;


public class MaximumSumSubMatrix {

    private static int[][] matrix = new int[][]  {{ 1, 2, -1, -4, 20},
    	                                          {-8, -3, 4, 2,  1},
    	                                          { 3, 8, 10, 1,  3},
    	                                          {-4, -1, 1, 7, -6}}; 
	
    
    private static int top, bottom;
    
    private static int getMaxSum (int[] arr) {
    	int[] sumArray = new int[arr.length];
		int[] sumStart = new int[arr.length];
		
		sumArray[0] = arr[0];
		int maxSumIndex = 0;
		for (int i=1; i<arr.length; i++) {
			int newSum = sumArray[i-1] + arr[i];
			if (newSum > arr[i]) {  //Add element to the existing sum chain 
				sumArray[i] = newSum;
				sumStart[i] = sumStart[i-1];
			} else {    //Start new chain with the current element as start element
				sumArray[i] = arr[i];
				sumStart[i] = i;
				newSum = arr[i];
			}
			
			if (sumArray[maxSumIndex] < newSum) {
				maxSumIndex = i;
			}
		}
        
		bottom = maxSumIndex;
		top =  sumStart[maxSumIndex];
		
        return sumArray[maxSumIndex];
    }
    
    public static void main (String args[]) {
    	int rows = matrix.length;
    	int cols = matrix[0].length;
    	
    	int maxSum = Integer.MIN_VALUE;
    	int finalLeft=0, finalTop=0, finalRight=0, finalBottom=0;
    	
    	for (int left=0; left<cols; left++) {
    		int [] temp = new int[rows];  //Stores the sum for all the columns from left to right in a column 
    		for (int right=left; right<cols; right++) {
    	    	for (int k=0; k<rows; k++) {
    	    		temp[k] +=  matrix[k][right];
    	    	}
    	    	int iMaxSum = getMaxSum(temp);
    	    	if (iMaxSum > maxSum) {
    	    	    maxSum = iMaxSum;
    	    	    finalLeft = left;
    	    	    finalRight = right;
    	    	    finalTop = top;
    	    	    finalBottom = bottom;
    	    	}
    	    }
    	}
    	
    	System.out.println("Max sum is:" + maxSum);
        System.out.println("Upper point is:(" + finalTop + "," +  + finalLeft + ")");
        System.out.println("Lower point is:(" + finalBottom + "," + finalRight + ")");
    }
}
