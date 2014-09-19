package com.sunny.array;

public class ReplaceElementWithGreatestElement {

	public static void main (String args[]) {
		int[] arr = new int[]  {1,2,3,4,5,6};
		int i = arr.length - 2;
		int greatest = arr[arr.length - 1];
		arr[arr.length - 1] = -1;
		while (i >= 0) {
			if (arr[i] < greatest) {
				int temp = arr[i];
				arr[i] = greatest;
				greatest = temp;
			} else {
				greatest = arr[i];
				int k = i+1;
				while (k < arr.length) {
					if (arr[i] < arr[k]) {
						arr[i] = arr[k];
						break;
					}
					k++;
				}
			}
			i--;
		}
		
		for (i=0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
}
