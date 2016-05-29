package week6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MedianMaintainenceAssignment {
	
	private static int size =  10000;

	private static int[] numbers =  new int[size];
	
	private static int[] medians = new int[size];
	
	public static void main (String args[]) throws IOException {
		readFile("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 1\\Week 6\\ProgrammingAssignment\\Median.txt");
		computeMedians();
		int medianMod = 0;
		for (int i=0; i<size; i++) {
			medianMod += medians[i] % size;
		}
		System.out.println(medianMod  % size);
	}
	
	private static void computeMedians () {
		MinHeap minHeap = new MinHeap(size/2);
		MaxHeap maxHeap = new MaxHeap(size/2);
		
		maxHeap.addElement(numbers[0]);
		medians[0] = maxHeap.getMax();
		
		int element  = numbers[1];
		if (element < maxHeap.getMax()) {
            minHeap.addElement(maxHeap.getMax());
            maxHeap.delete();
            maxHeap.addElement(element);
		} else {
			minHeap.addElement(numbers[1]);
		}
		medians[1] = maxHeap.getMax();
		
		for (int i=2; i<size; i++) {
			element = numbers[i];
			if (element < maxHeap.getMax()) { //Left
				if (maxHeap.getCurrentSize() <=  minHeap.getCurrentSize()) {
					maxHeap.addElement(element);
				} else {
					minHeap.addElement(maxHeap.getMax());
		            maxHeap.delete();
		            maxHeap.addElement(element);
				}
			} else if (element > minHeap.getMin()) { //Right
				if (minHeap.getCurrentSize() <=  maxHeap.getCurrentSize()) {
					minHeap.addElement(element);
				} else {
					maxHeap.addElement(minHeap.getMin());
		            minHeap.delete();
		            minHeap.addElement(element);
				}
			} else { //In Between
				if (minHeap.getCurrentSize() <=  maxHeap.getCurrentSize()) {
					minHeap.addElement(element);
				} else {
					maxHeap.addElement(element);
				}
			}
			
			if (i%2 == 0) {//Means no. of elements are odd
				if (maxHeap.getCurrentSize() > minHeap.getCurrentSize()) {
					medians[i] = maxHeap.getMax();
				} else {
					medians[i] = minHeap.getMin();
				}
			} else { //Means no. of elements are even
				medians[i] = maxHeap.getMax();
			}
		}
	}
	
	private static void readFile(String filePath) throws IOException {
		BufferedReader in = null;
		int i=0;
		try {
			in = new BufferedReader(new FileReader(filePath));
			String line = in.readLine();
			while (line != null) {
				numbers[i] = Integer.parseInt(line.trim());
				i++;
				line = in.readLine();
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}