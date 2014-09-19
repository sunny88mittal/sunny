/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorting;

/**
 * http://en.wikipedia.org/wiki/Counting_sort
 * @author sumittal
 */

public class CountingSort {
	
	public static void main (String args[]) {
	
		int[] array = new int[]{1,5,3,6,9,8};
		
		int arrayLength = array.length;
		int maxElement = array[0];
		
		//Find the max element
		for (int i=1; i<arrayLength; i++) {
			int temp = array[i];
			if (temp > maxElement) {
				maxElement = temp; 
			}
		}

		//Store the frequency of numbers
		int[] tempArray = new int[maxElement + 1];
		for (int i=0; i<arrayLength; i++) {
			tempArray[array[i]]++;
		}
		
		//Store the first position at which a number should appear in the output array
		int total = 0;
		int tempArrayLength = maxElement + 1;
		for (int i=0; i<tempArrayLength; i++) {
			int count  = tempArray[i];
			tempArray[i] = total;
			total = total + count;
		}
		
		//Put the numbers in the output array
		int[] output = new int [arrayLength];
		for (int i=0; i<arrayLength; i++) {
			int item = array[i];
			output[tempArray[item]] = item;
			tempArray[item]++;
		}
		
		for (int i=0; i<arrayLength; i++) {
			System.out.println(output[i]);
		}
	}
}
