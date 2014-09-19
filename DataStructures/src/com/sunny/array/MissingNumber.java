package com.sunny.array;

/**
 * http://www.geeksforgeeks.org/find-the-missing-number/
 * @author sumittal
 *
 */
public class MissingNumber {

	public static void main (String args[]) {
		int[] numbers =  new int[] {1, 2, 5, 6, 3, 7, 8};
		int xorOfGivenNumbers = 0;
		for (int i=0; i<numbers.length; i++) {
			xorOfGivenNumbers = xorOfGivenNumbers ^ numbers[i]; 
		}
		
		int xorOfAllNumbers = 0;
		for (int i=1; i<=numbers.length+1; i++){
			xorOfAllNumbers = xorOfAllNumbers ^ i; 
		}
		
		System.out.println("Missing number is:" + (xorOfAllNumbers ^ xorOfGivenNumbers));
	}
}
