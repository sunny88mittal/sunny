package week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RSelect {
	
	public static void main (String args[]) {
		List<Integer> numbers = getDataSet();
		System.out.println("Ith Order statistics is:" + getIthStatistics(numbers.toArray(new Integer[] {}), 17));
	}

	private static int getIthStatistics (Integer[] numbers, int i) {
		int size = numbers.length;
		if (size == 1) {
			return numbers[0];
		}
		
		//Choose a random number from the array which will be pivot
		Random randomGen = new Random();
		int randomPivot = randomGen.nextInt(size);
		if (randomPivot == size) {
			randomPivot = randomPivot -1;
		}
		int pivotElement = numbers[randomPivot];
		
		//Move the pivot element to start position
		int temp = numbers[0];
		numbers[0] = pivotElement;
		numbers[randomPivot] = temp;
		
		//Partition the array around the pivot such that L<P<=R
		int pivotBoundary = 1;
		for (int j=1; j<size; j++) {
			if (numbers[j] < pivotElement) {
				temp = numbers[pivotBoundary];
				numbers[pivotBoundary] = numbers[j];
				numbers[j] = temp;
				pivotBoundary++;
			}
		}
		
		//Move the pivot element to correct Position
		--pivotBoundary;
		temp = numbers[pivotBoundary];
		numbers[pivotBoundary] = pivotElement;
	    numbers[0] = temp;
	    
	    //Check and Recurse
	    if (i == pivotBoundary) {
	    	return pivotElement;
	    } else if (i < pivotBoundary) {
	    	return getIthStatistics(Arrays.copyOfRange(numbers, 0, pivotBoundary),  i);
	    } else {
	    	return getIthStatistics(Arrays.copyOfRange(numbers, pivotBoundary+1, size), i-(pivotBoundary+1));
	    }
	}
	
	private static List<Integer> getDataSet() {
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i=1; i<=50; i++) {
			numbers.add(i);
		}
		Collections.shuffle(numbers);
		
		return numbers;
	}
}
