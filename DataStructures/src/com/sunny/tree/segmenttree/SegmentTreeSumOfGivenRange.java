package com.sunny.tree.segmenttree;

//http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/

public class SegmentTreeSumOfGivenRange {

	
	//Methods to update the value
	private static void updateValue (int arr[], int[] st, int i, int newVal) {
		int length = arr.length;
		if (i < 0 || i > length - 1) {
			return;
		}
		
		int diff = newVal - arr[i];
		arr[i] = newVal;
		updateValueUtil(st, 0, length - 1, i, diff, 0);
	}
	
	private static void updateValueUtil(int[] st, int ss, int se, int i, int diff, int index) {
		//If the position is not in range return
		if (i < ss || i > se) {
			return;
		}
		
		// If the input index is in range of this node, then update the value
	    // of the node and its children
	    st[index] = st[index] + diff;
	    if (se != ss)
	    {
	        int mid = (ss+ se)/2;
	        updateValueUtil(st, ss, mid, i, diff, 2*index + 1);
	        updateValueUtil(st, mid+1, se, i, diff, 2*index + 2);
	    }
	}

	//Methods to get the sum for the range
	private static int getSum (int[] st, int n, int qs, int qe) {
		if (qs < 0 || qe > n-1 || qe < qs) {
			return -1;
		}
		return getSumUtil(st, 0, n-1, qs, qe, 0);
	}	
	
	private static int getSumUtil(int[] st, int ss, int se, int qs, int qe, int index) {
		// If segment of this node is a part of given range, then return the
	    // sum of the segment
	    if (qs <= ss && qe >= se)
	        return st[index];
	    
	    // If segment of this node is outside the given range
	    if (se < qs || ss > qe)
	        return 0;
	 
	    // If a part of this segment overlaps with the given range
	    int mid = (ss + se)/2;
	    return getSumUtil(st, ss, mid, qs, qe, 2*index+1) +
	           getSumUtil(st, mid+1, se, qs, qe, 2*index+2);
	}
	
	//Methods to construct the segment tree
	private static int[] constructSTTee(int[] arr) {
		int stSize = (int) (2* Math.pow(2, Math.ceil(Math.log(arr.length)/ Math.log(2))));
		int[] st = new int[stSize];
	    constructSTUtil(arr, 0, arr.length-1, st, 0);	
		return st;
	}
	
	/**
	 * An array representation of tree is used to represent Segment Trees. 
	 * For each node at index i, 
	 *    the left child is at index 2*i+1, 
	 *    right child at 2*i+2 
	 *    and the parent is at floor ((i-1)/2)
	 */
	private static int constructSTUtil(int[] arr, int ss, int se, int[] st, int si) {
		// If there is one element in array, store it in current node of
	    // segment tree and return
		if (ss == se) {
			st[si] = arr[ss];
			return arr[ss];
		}
		
		// If there are more than one elements, then recur for left and
	    // right subtrees and store the sum of values in this node
		int mid = (ss + se) / 2;
		st[si] = constructSTUtil(arr, ss, mid, st, 2*si + 1) +
				constructSTUtil(arr, mid+1, se, st, 2*si + 2);
		return st[si];
	}
	
	public static void main (String args[]) {
		int arr[] = {1, 3, 5, 7, 9, 11};		
		int st[] = constructSTTee(arr);
		System.out.println(getSum(st, arr.length, 2, 4));
		updateValue(arr, st, 2, 16);
		System.out.println(getSum(st, arr.length, 2, 4));
	}
}
