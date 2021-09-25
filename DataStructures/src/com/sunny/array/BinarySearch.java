package com.sunny.array;

import junit.framework.Assert;

public class BinarySearch {

	public static void main(String args[]) {
		int arr[] = new int[] { 1, 2, 3, 5, 8, 12, 14, 16, 20, 22, 27, 32 };
	
		Assert.assertEquals(binarySearch(0, arr.length - 1, arr, 16), 7);
		Assert.assertEquals(binarySearch(0, arr.length - 1, arr, 15), -1);
		Assert.assertEquals(binarySearch(0, arr.length - 1, arr, 1), 0);
		Assert.assertEquals(binarySearch(0, arr.length - 1, arr, 27), 10);
		Assert.assertEquals(binarySearch(0, arr.length - 1, arr, 32), 11);
		Assert.assertEquals(binarySearch(0, arr.length - 1, arr, 35), -1);
	}

	private static int binarySearch(int start, int end, int[] arr, int element) {
		int mid = (start + end) / 2;

		if (element == arr[mid]) {
			return mid;
		}

		if (start == end) {
			return -1;
		}

		if (element < arr[mid]) {
			return binarySearch(start, mid - 1, arr, element);
		} else {
			return binarySearch(mid + 1, end, arr, element);
		}
	}
}
