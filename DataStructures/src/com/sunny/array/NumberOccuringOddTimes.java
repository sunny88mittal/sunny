package com.sunny.array;

/**
 * http://www.geeksforgeeks.org/find-the-number-occurring-odd-number-of-times/
 * @author sumittal
 *
 */
public class NumberOccuringOddTimes {
     public static void main (String args[]) {
    	 int[] numbers =  new int[] {1, 2, 3, 2, 3, 1, 3};
    	 int xor = numbers[0];
    	 for(int i=1; i<numbers.length;i++) {
    		 xor = xor ^ numbers[i];
    	 }
    	 
    	 System.out.println("Number is:" + xor);
     }
}
