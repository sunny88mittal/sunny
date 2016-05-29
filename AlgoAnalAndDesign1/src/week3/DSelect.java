package week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DSelect {

	public static void main (String args[]) {
		int[] numbers = getDataSet();
		System.out.println("Ith Order statistics is:" + getIthStatistics(numbers, 47));
	}

	private static int getIthStatistics (int [] numbers, int i) {
		int size = numbers.length;
		if (size <= 5) {
			Arrays.sort(numbers);
			return numbers[i];
		}
		
		//Find the Pivot
		int noOfSubArrays = size/5;
		if (size % 5 != 0) {
			noOfSubArrays++;
		}
		int[] medians = new int[noOfSubArrays];
		
		for (int j=0; j<noOfSubArrays; j++) {
			int start = j*5;
			int end = start + 5;
			if (end > size) {
				end = size;
			}
		    medians[j] = getIthStatistics(Arrays.copyOfRange(numbers, start, end), (end-start)/2);
		}
		
		int medianOfMedians = noOfSubArrays/2;
		if (noOfSubArrays % 2 == 0) {
			--medianOfMedians;
		}
		int pivot = getIthStatistics(medians, medianOfMedians);
		
		//Move the pivot element to 0 position
		int originalPosition = 0;
		for (int j=0; j<size; j++) {
			if (numbers[j] == pivot) {
				originalPosition = j;
				break;
			}
		}
		int temp = numbers[0];
	    numbers[0] = pivot;
	    numbers[originalPosition] = temp;
		
		//Partition the array around the pivot such that L<P<=R
		int pivotBoundary = 1;
		for (int j=1; j<size; j++) {
			if (numbers[j] < pivot) {
				temp = numbers[pivotBoundary];
				numbers[pivotBoundary] = numbers[j];
				numbers[j] = temp;
				pivotBoundary++;
			} 
		}	
		
		//Move the pivot element to correct Position
		--pivotBoundary;
		temp = numbers[pivotBoundary];
		numbers[pivotBoundary] = pivot;
	    numbers[0] = temp;

	    //Check and Recurse
	    if (i == pivotBoundary) {
	    	return pivot;
	    } else if (i < pivotBoundary) {
	    	return getIthStatistics(Arrays.copyOfRange(numbers, 0, pivotBoundary),  i);
	    } else {
	    	return getIthStatistics(Arrays.copyOfRange(numbers, pivotBoundary+1, size), i-pivotBoundary-1);
	    }
	}
	
	private static int[] getDataSet() {
		int size = 50;
		int [] numbersArray = new int[size];
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i=1; i<=size; i++) {
			numbers.add(i);
		}
		Collections.shuffle(numbers);
		
		for (int i=0; i<size; i++) {
			numbersArray[i] = numbers.get(i);
		}
		
		return numbersArray;
	}
}
