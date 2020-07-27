/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorting;

import java.util.Arrays;

/**
 *
 * @author sumittal
 */
public class QuickSort {

	public static void main(String args[]) {
		int arr[] = new int[] { 10, 80, 90, 30, 40, 50, 70 };
		quickSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}
	
	private static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			// Get last element to correct position
			int partitionIndex = partition(arr, low, high);

			// Sort left half
			quickSort(arr, low, partitionIndex - 1);

			// Sort right half
			quickSort(arr, partitionIndex + 1, high);
		}
	}

	private static int partition(int[] arr, int low, int high) {
		// Move all elements smaller to pivot to the left
		int pivot = arr[high];
		int i = low - 1;

		for (int j = low; j < high; j++) {
			if (arr[j] < pivot) {
				i++;

				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		// Put the pivot in the correct position
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		return i + 1;
	}
}
