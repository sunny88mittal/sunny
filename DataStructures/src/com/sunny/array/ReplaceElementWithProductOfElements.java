package com.sunny.array;

public class ReplaceElementWithProductOfElements {
	
	public static void main (String args[]) {
		int[] arr = new int[]  {1,2,3,4,5,6};
		
		int length = arr.length;
		int[] leftProducts = new int[length];
		int[] rightProducts = new int[length];
		
		leftProducts[0] = 1;
		rightProducts[length-1] = 1;
		
		for (int i=1; i<length; i++) {
			leftProducts[i] = leftProducts[i-1] * arr[i-1];
		}
		
		for (int i=length-2; i>=0; i--) {
			rightProducts[i] = rightProducts[i+1] * arr[i+1];
		}
		
		for (int i=0; i<length ;i++) {
			arr[i] = leftProducts[i] * rightProducts[i];
		}
		
		for (int i=0; i<length ;i++) {
			System.out.println(arr[i]);
		}
	}
}
