package com.sunny.array;

public class UnionIntersesctionSortedArrays {

	public static void main(String args[]) {
		int arr1[] = new int[] { 1, 3, 4, 5, 7 };
		int arr2[] = new int[] { 2, 3, 5, 6 };
		System.out.println("Printing Intersection");
		printInteresection(arr1, arr2);
		System.out.println("Printing Union");
		printUnion(arr1, arr2);
	}

	
	private static void printInteresection(int arr1[], int arr2[]) {
		int i = 0, j = 0;

		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] < arr2[j]) {
				i++;
			} else if (arr1[i] > arr2[j]) {
				j++;
			} else {
				System.out.println(arr1[i]);
				i++;
				j++;
			}
		}
	}
	
	private static void printUnion(int arr1[], int arr2[]) {
		int i = 0, j = 0;

		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] < arr2[j]) {
				System.out.println(arr1[i]);
				i++;
			} else if (arr1[i] > arr2[j]) {
				System.out.println(arr2[j]);
				j++;
			} else {
				System.out.println(arr1[i]);
				i++;
				j++;
			}
		}

		if (i < arr1.length) {
			for (int k = i; k < arr1.length; k++) {
				System.out.println(arr1[k]);
			}
		}

		if (j < arr2.length) {
			for (int k = j; k < arr2.length; k++) {
				System.out.println(arr2[k]);
			}
		}
	}
}
