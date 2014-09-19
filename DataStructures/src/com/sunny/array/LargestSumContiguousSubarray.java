package com.sunny.array;


/**
 * http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 * @author sumittal
 *
 */
public class LargestSumContiguousSubarray {
	
	public static void main (String args[]) {
		int[] arr = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
		arr = new int[]{1,-8,3,-4};
		
		int[] sumArray = new int[arr.length];
		int[] sumStart = new int[arr.length];
		
		sumArray[0] = arr[0];
		int maxSumIndex = 0;
		for (int i=1; i<arr.length; i++) {
			int newSum = sumArray[i-1] + arr[i];
			if (newSum > arr[i]) {
				sumArray[i] = newSum;
				sumStart[i] = sumStart[i-1];
			} else {
				sumArray[i] = arr[i];
				sumStart[i] = i;
				newSum = arr[i];
			}
			
			if (sumArray[maxSumIndex] < newSum) {
				maxSumIndex = i;
			}
		}
		
		//Print the sum and elements
		System.out.println("Max sum is:" + sumArray[maxSumIndex]);
		for (int i=sumStart[maxSumIndex]; i<=maxSumIndex; i++) {
			System.out.println(arr[i]);
		}
	}
}
