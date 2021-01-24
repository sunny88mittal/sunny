package com.sunny.array;


/**
 * https://www.geeksforgeeks.org/array-rotation/
 * @author sunmitta
 */
public class ArrayRotation {

	public static void main(String args[]) {
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		rotateArray(arr, 3);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	private static void rotateArray(int[] arr, int d) {
		reverse(arr, 0, d-1);
		reverse(arr, d, arr.length-1);
		reverse(arr, 0, arr.length-1);
	}

	private static void reverse(int[] arr, int start, int end) {
		while (start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
	}

}
