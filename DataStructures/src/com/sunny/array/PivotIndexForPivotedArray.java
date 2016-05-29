package com.sunny.array;

public class PivotIndexForPivotedArray {
	
	public static void main (String args[]) {
		int[] arr = new int[]{};
		System.out.println(getPivot(arr));
		
		arr = new int[]{1};
		System.out.println(getPivot(arr));
		
		arr = new int[]{2,1};
		System.out.println(getPivot(arr));

		arr = new int[]{3,4,5,2,1};
		System.out.println(getPivot(arr));
		
		arr = new int[]{3,5,4,2,1};
		System.out.println(getPivot(arr));
		
		arr = new int[]{3,5,4,2,1,-1,-2,-5,-6,-7};
		System.out.println(getPivot(arr));
		
		arr = new int[]{30,40,50,60,70,80,90,100,110, 105};
		System.out.println(getPivot(arr));
	}
	
	private static int getPivot(int[] arr) {
		int low = 0;
		int high = arr.length - 1;
		
		while (true) {
			if (high - low <2) return -1; 
			
			int mid = (high + low)/2;
			
			if (arr[mid] > arr[mid-1]) {
				if (arr[mid] > arr[mid+1]) {
					return mid;
				}
				low = mid;
				continue;
			}
			
			high = mid;
		}
	}
}
