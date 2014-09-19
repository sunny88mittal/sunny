package com.sunny.queue;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/find-the-largest-number-multiple-of-3/
 * @author sumittal
 *
 */
public class LargestMultipleOf3 {
	
	public static void main (String args[]) {
		int [] numbers = new int[] {8,1,7,6,0,2,4};
		int rem1num1=-1, rem1num2=-1;
		int rem2num1 =-1, rem2num2=-1;
		
		Arrays.sort(numbers);
		int sum = 0;
		for (int i=0; i<numbers.length; i++) {
			sum += numbers[i];
		}
		
		if (sum %3 != 0) {
			for (int i=0; i<numbers.length; i++) {
				if (numbers[i] %3 == 1 && rem1num1 == -1) {
					rem1num1 = numbers[i];
				} else if (numbers[i] %3 == 1 && rem1num2 == -1) {
					rem1num2 = numbers[i];
				} else if (numbers[i] %3 == 2 && rem2num1 == -1) {
					rem2num1 = numbers[i];
				} else if (numbers[i] %3 == 2 && rem2num2 == -1) {
					rem2num2 = numbers[i];
				}
			}
		}
		
		if (sum % 3 == 1) {
			//Skip either 
			//rem1num1 or 
			//rem2num1 and rem2num2
		    if (rem1num1 !=-1) {
		    	printArray(numbers, rem1num1, -1);
		    } else {
		    	printArray(numbers, rem2num1, rem2num2);
		    }
		}
		
		if (sum % 3 == 2) {
			//Skip either 
			//rem2num1 or 
			//rem1num1 and rem1num2
			if (rem2num1 !=-1) {
		    	printArray(numbers, rem2num1, -1);
		    } else {
		    	printArray(numbers, rem1num1, rem1num2);
		    }
		}
	}
	
	private static void printArray(int [] numbers, int toskip1, int toSkip2) {
		for (int i=0; i<numbers.length; i++) {
			if (numbers[i]!= toskip1 && numbers[i] != toSkip2) {
				System.out.println(numbers[i]);
			}
		}
	}
}
