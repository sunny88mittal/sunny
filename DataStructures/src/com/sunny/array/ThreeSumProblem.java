package com.sunny.array;

import java.util.Arrays;

/**
 * 3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time proportional to N2 in the worst case. 
 * You may assume that you can sort the N integers in time proportional to N2 or better.
 * 
 * http://en.wikipedia.org/wiki/3SUM
 * 
 * @author sumittal
 *
 */

public class ThreeSumProblem {

	public static void main (String args[]) {
		int[] arr = new int [] {-25, -10, -7, -3, 2, 4, 8, 10};
		checkfor3sum(-20, arr);
		checkfor3sum(40, arr);
		checkfor3sum(0, arr);
		checkfor3sum(22, arr);
		checkfor3sum(-42, arr);
		checkfor3sum(42, arr);
		checkfor3sum(50, arr);
	}
	
	public static void checkfor3sum(int ask, int[] arr) {
        int size = arr.length;
        boolean found = false;
        Arrays.sort(arr);
		for (int i=0; i<size-2;  i++) {
			int a  = arr[i];
			int p1 = i+1;
			int p2 = size-1; 
			int b = arr[p1];
			int c = arr[p2];
			while (p1 < p2) {
				b = arr[p1];
				c = arr[p2];
				if (a + b + c < ask) {
					p1++;
				} else if (a + b + c > ask) {
					p2--;
				} else {
					found = true;
					break;
				}
			}
			if (found) {
				System.out.println(a + "," + b + "," + c);
				break;
			}
	    }
		
		if (!found)  {
			System.out.println("No Result");
		}
	}
}
