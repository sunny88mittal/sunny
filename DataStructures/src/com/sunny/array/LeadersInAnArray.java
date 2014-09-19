package com.sunny.array;


/**
 * Write a program to print all the LEADERS in the array. An element is leader if it is greater than all the elements to its right side. 
 * And the rightmost element is always a leader. For example int the array {16, 17, 4, 3, 5, 2}, leaders are 17, 5 and 2. 
 * http://www.geeksforgeeks.org/leaders-in-an-array/
 * @author sumittal
 *
 */
public class LeadersInAnArray {
	
	public static void main (String args[]) {
		int numbers[] = new int [] {16, 17, 4, 3, 5, 2};
		
		int max = numbers[numbers.length-1];
		System.out.println(numbers[numbers.length-1]);
		for(int i=numbers.length-2; i>=0; i--) {
		   if(numbers[i] > max) {
			   max = numbers[i];
			   System.out.println(numbers[i]);
		   }
		}
	}

}
