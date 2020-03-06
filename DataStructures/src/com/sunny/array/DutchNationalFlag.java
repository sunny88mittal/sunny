package com.sunny.array;

import java.util.Arrays;

/**
 * Dutch national flag. Given an array of N buckets, each containing a red, white, or blue pebble, sort them by color. The allowed operations are:
      swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
      color(i): color of pebble in bucket i.

      The performance requirements are as follows:
         At most N calls to color().
         At most N calls to swap().
         Constant extra space.
 
   http://www.csse.monash.edu.au/~lloyd/tildeAlgDS/Sort/Flag/
 
 * @author sumittal
 *
 */

public class DutchNationalFlag {

	public static void main (String args[]) {
		int arr[] = new int[] {0,1,2,2,2,2,1,1,0,1,2,0,2,1,0,2,0,0,0,0,1,2,1,0};
		
		int length = arr.length;
		
		int lo = 0;
		int mid = 0;
		int high = length-1;
		
		//We are maintaining four arrays (0-lo), (lo-mid), (mid-high), (high-end)
		//We will shrink the mid-high array which contains unknown numbers
		
		while (mid <= high) {
			int x = arr[mid];
			if (x == 0) {
				int temp = arr[lo];
				arr[lo] = x;
				arr[mid] = temp;
				lo++;
				mid++;
			} else if (x == 2) {
				int temp = arr[high];
				arr[high] = x;
				arr[mid] = temp;
				high--;
			} else if (x == 1) {
				mid++;
			}
		}
		
		System.out.println(Arrays.toString(arr));
	}
}
