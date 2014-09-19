package com.sunny.array;

import java.util.Arrays;

public class TwoSumProblem {
	
	public static void main (String args[]) {
	   int numbers[] = new int[] {1, 4, 45, 6, 10, -8};
	   int toSum = 55;
	   Arrays.sort(numbers);
	   int start = 0, end = numbers.length-1;
	   boolean found = false;
	   while (start < end) {
		   int sum = numbers[start] + numbers[end];
		   if (sum == toSum) {
			   System.out.println("Numbers are:" + numbers[start] + "," + numbers[end]);
			   found = true;
			   break;
		   }
		   if (sum > toSum) {
			   end--;
		   } else {
			   start++;
		   }
	   }
	   if (!found) {
		   System.out.println("Numbers not found");
	   }
	}
}
