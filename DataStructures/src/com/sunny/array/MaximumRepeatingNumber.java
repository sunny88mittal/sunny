package com.sunny.array;

/**
 * Given an array of size n, the array contains numbers in range from 0 to k-1 where k is a positive integer and k <= n. Find the maximum repeating number in this array. 
 * For example, let k be 10 the given array be arr[] = {1, 2, 2, 2, 0, 2, 0, 2, 3, 8, 0, 9, 2, 3}, the maximum repeating number would be 2.
 * Expected time complexity is O(n) and extra space allowed is O(1). Modifications to array are allowed. 
 * http://www.geeksforgeeks.org/find-the-maximum-repeating-number-in-ok-time/
 * @author sumittal
 *
 */
public class MaximumRepeatingNumber {

	public static void main (String args[]) {
		int[] numbers = new int[] {1, 2, 2, 2, 0, 2, 0, 2, 3, 8, 0, 9, 2, 3};
		int k = numbers.length;
		//Keep on increasing the value at the index defined by numbers[i]
		for (int i=0; i<numbers.length; i++) {
		    int rem = numbers[i] % k;
		    numbers[rem] = numbers[rem] + k;
		}
		
		int max = 0;
		for (int i=1; i<numbers.length; i++) {
			if (numbers[max] < numbers[i]) {
				max = i;
			}
		}
		
		System.out.println("Maximum repeating number is:" + numbers[max] % k);
	}
}
