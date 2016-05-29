package week2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AssignmentQuickSort {

	public static void main (String args[]) throws IOException {
 		int[] elements = readFile("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithm Courses\\Stanford\\Algorithm Analysis and Design 1\\Week 2\\ProgrammingAssinment\\QuickSort.txt", 10000);
		//int[] elements = readFile("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithm Courses\\Stanford\\Algorithm Analysis and Design 1\\Week 2\\ProgrammingAssinment\\TestArray.txt", 1000);
		long comparions = sortWithMedianPivot(elements, 0, elements.length);
		System.out.println("No of compariosn are:" +  comparions);	
	}
	
	/**
	 * Steps
	 * 1. Choose the pivot
	 * 2. Put the pivot element at the correct position
	 * 3. Sort the sub arrays recursively
	 * @param array
	 * @param start
	 * @param end
	 * @return The no of swaps
	 */
	private static long sortWithStartPivot(int[] array, int start, int end) {
		long comparisons = 0;
		if ((end - start) > 1) {
			int pivotBoundary = start+1, 
				pivotElement = array[start];
			
		    for (int i=start+1; i<end; i++) {
		    	if (array[i] < pivotElement) {
		    		int temp = array[pivotBoundary];
		    		array[pivotBoundary] = array[i];
		    		array[i] = temp;
		    		pivotBoundary++;
		    	}
		    }
		    
		    int temp = array[pivotBoundary-1];
		    array[pivotBoundary-1] = pivotElement;
		    array[start] = temp;
		    
		    comparisons = end - start - 1;
		    comparisons += sortWithStartPivot(array, start, pivotBoundary-1);
		    comparisons += sortWithStartPivot(array, pivotBoundary, end);
		}
		return comparisons;
	}
	
	/**
	 * Steps
	 * 1. Choose the pivot
	 * 2. Put the pivot element at the correct position
	 * 3. Sort the sub arrays recursively
	 * @param array
	 * @param start
	 * @param end
	 * @return The no of swaps
	 */
	private static long sortWithEndPivot(int[] array, int start, int end) {
		long comparisons = 0;
		if ((end - start) > 1) {
			//Swap first element with last to make the first element as the pivot
			int temp = array[start];
			array[start] = array[end-1];
			array[end-1] = temp;
			
			int pivotBoundary = start+1, 
				pivotElement = array[start];
			
		    for (int i=start+1; i<end; i++) {
		    	if (array[i] < pivotElement) {
		    		temp = array[pivotBoundary];
		    		array[pivotBoundary] = array[i];
		    		array[i] = temp;
		    		pivotBoundary++;
		    	}
		    }
		    
		    temp = array[pivotBoundary-1];
		    array[pivotBoundary-1] = pivotElement;
		    array[start] = temp;
		    
		    comparisons = end - start - 1;
		    comparisons += sortWithEndPivot(array, start, pivotBoundary-1);
		    comparisons += sortWithEndPivot(array, pivotBoundary, end);
		}
		return comparisons;
	}
	
	
	/**
	 * Steps
	 * 1. Choose the pivot
	 * 2. Put the pivot element at the correct position
	 * 3. Sort the sub arrays recursively
	 * @param array
	 * @param start
	 * @param end
	 * @return The no of swaps
	 */
	private static long sortWithMedianPivot(int[] array, int start, int end) {
		long comparisons = 0;
		int length = end - start;
		if ((end - start) > 1) {
			//Swap first element with last to make the first element as the pivot
			int middle = (start + end)/2;
			if (length % 2 == 0) {
				middle = middle - 1;
			}
			int a = array[start];
			int b = array[middle];
			int c = array[end-1];
			
			if ( (a - b) * (c - a) >= 0 ) { 
			    //No swap required
			} else if ( (b - a) * (c - b) >= 0 ) {
				array[middle] = a;
				array[start] = b;
			} else {
				array[end-1] = a;
				array[start] = c;
			}		        
			
			//Start quick sort
			int pivotBoundary = start+1, 
				pivotElement = array[start];
			
		    for (int i=start+1; i<end; i++) {
		    	if (array[i] < pivotElement) {
		    		int temp = array[pivotBoundary];
		    		array[pivotBoundary] = array[i];
		    		array[i] = temp;
		    		pivotBoundary++;
		    	}
		    }
		    
		    int temp = array[pivotBoundary-1];
		    array[pivotBoundary-1] = pivotElement;
		    array[start] = temp;
		    
		    comparisons = end - start - 1;
		    comparisons += sortWithMedianPivot(array, start, pivotBoundary-1);
		    comparisons += sortWithMedianPivot(array, pivotBoundary, end);
		}
		return comparisons;
	}
	
	public static int[] readFile (String fileLocation, int noOfElements) throws IOException {
		int[] elements =  new int[noOfElements];
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileLocation));
			String line = in.readLine();
			int i =0;
			while (line != null) {
				elements[i] = Integer.parseInt(line);
				line = in.readLine();
				i++;
			}
			return elements;	
		} finally {
		    if (in != null) {
		    	in.close();
		    }
		}
	}
}
