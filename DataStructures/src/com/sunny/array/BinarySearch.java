package com.sunny.array;

public class BinarySearch {

	public static void main(String args[]) {
		int arr[] = new int[] { 1, 2, 3, 5, 8, 12, 14, 16, 20, 22, 27, 32 };
		System.out.println(binarySearch(0, arr.length - 1, arr, 16));
		System.out.println(binarySearch(0, arr.length - 1, arr, 15));
		System.out.println(binarySearch(0, arr.length - 1, arr, 1));
		System.out.println(binarySearch(0, arr.length - 1, arr, 27));
		System.out.println(binarySearch(0, arr.length - 1, arr, 32));
		System.out.println(binarySearch(0, arr.length - 1, arr, 35));
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
