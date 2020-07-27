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
public class MergeSort {

	public static void main(String args[]) {
		int[] arr = new int[] { 10, 20, 5, 4, 25, 3, 35, 1, 55 };
		int[] result = mergesort(arr);
		int length = result.length;
		for (int i = 0; i < length; i++) {
			System.out.println(result[i]);
		}
	}

	private static int[] mergesort(int[] arr) {
		int length = arr.length;
		if (length == 1) {
			return arr;
		}

		int mid = length / 2;
		return combine(mergesort(Arrays.copyOfRange(arr, 0, mid)), mergesort(Arrays.copyOfRange(arr, mid, length)));
	}

	private static int[] combine(int arr1[], int arr2[]) {
		int[] arr = new int[arr1.length + arr2.length];

		int arr1Index = 0;
		int arr2Index = 0;
		int combinedArrIndex = 0;

		// Combine elements from arr1 and arr2
		while (arr1Index < arr1.length && arr2Index < arr2.length) {
			if (arr1[arr1Index] < arr2[arr2Index]) {
				arr[combinedArrIndex] = arr1[arr1Index];
				arr1Index++;
			} else {
				arr[combinedArrIndex] = arr2[arr2Index];
				arr2Index++;
			}
			combinedArrIndex++;
		}

		// Remaining elements from array1
		while (arr1Index < arr1.length) {
			arr[combinedArrIndex] = arr1[arr1Index];
			arr1Index++;
			combinedArrIndex++;
		}

		// Remaining elements from array2
		while (arr2Index < arr2.length) {
			arr[combinedArrIndex] = arr2[arr2Index];
			arr2Index++;
			combinedArrIndex++;
		}

		return arr;
	}
}