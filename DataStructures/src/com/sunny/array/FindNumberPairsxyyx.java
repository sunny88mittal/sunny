package com.sunny.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Find number of pairs such that x^y > y^x
   Given two arrays X[] and Y[] of positive integers, find number of pairs such that x^y > y^x where x is an element from X[] and y is an element from Y[].
 */

public class FindNumberPairsxyyx {

	public static void main (String args[]) {
		getPairs( new int[] {10, 19, 18}, new int[] {11, 15, 9});
		getPairs( new int[] {2, 1, 6}, new int[] {1, 5});
	}
	
	
	public static List<String> getPairs(int[]x, int[]y) {
		//if logx/x > logy/y then x^y > y^x
		List<String> pairs = new ArrayList<String>();
		float newx[] = new float[x.length];
		float newy[] = new float[y.length];
		
		for (int i=0; i<x.length; i++) {
		    int el = x[i];
		    if (el == 0) {
		    	newx[i] = 0;
		    	continue;
		    }
			newx[i] = (float) Math.log(x[i]) / x[i];
		}
		
        for (int j=0; j<y.length; j++) {
        	int el = y[j];
		    if (el == 0) {
		    	newy[j] = 0;
		    	continue;
		    }
			newy[j] = (float) Math.log(y[j]) / y[j];			
		}
        
        Arrays.sort(newy);
        
        for (int i=0; i<x.length; i++) {
        	float logxbyx = newx[i];
        	if (logxbyx != 0) {
        		
        	}
        }
		
		return pairs;
	}
	
	public int modifiedBinarySearch(float searchfor, float[] sortedArr) {
		int size = sortedArr.length; 
		int start = 0;
		int end = size - 1;
		if (searchfor <= sortedArr[start]) {
			return -1;
		}
		
		if (searchfor > sortedArr[end]) {
			return size;
		}
		
		start = (start + size)/2;
		while (true) {
			if (searchfor > sortedArr[start]) {
				
			}
		}
	}
	
}
