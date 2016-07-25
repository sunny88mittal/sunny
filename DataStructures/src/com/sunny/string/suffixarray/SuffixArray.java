package com.sunny.string.suffixarray;

import java.util.Arrays;

public class SuffixArray {

	private static class SuffixNode implements Comparable<SuffixNode> {
		int index;
		int[] rank = new int[2];

		@Override
		public int compareTo(SuffixNode o) {
			if (this.rank[0] != o.rank[0]) {
				return Integer.compare(this.rank[0], o.rank[0]);
			}
			return Integer.compare(this.rank[1], o.rank[1]);
		}
	}

	private static int[] getSuffixArray(String str) {
		int n = str.length();

		// Array to maintain indexes of suffixes
		SuffixNode[] suffixes = new SuffixNode[n];

		// Compute the initial positions and ranks for 1st and 2nd character
		for (int i = 0; i < n; i++) {
			SuffixNode suffixNode = new SuffixNode();
			suffixNode.index = i;
			suffixNode.rank[0] = str.charAt(i) - 'a';
			suffixNode.rank[1] = ((i + 1) < n) ? (str.charAt(i) - 'a') : -1;
			suffixes[i] = suffixNode;
		}

		// Sort the suffixes
		Arrays.sort(suffixes);

		// Sort the suffixes by using 2^k characters in each iteration
		int ind[] = new int[n]; // This array is needed to get the index in
								// suffixes[] from original index. This mapping
								// is needed to get next suffix.
		for (int k = 4; k < 2 * n; k = k * 2) {
			// Assigning rank and index values to first suffix
			int rank = 0;
			int prev_rank = suffixes[0].rank[0];
			suffixes[0].rank[0] = rank;
			ind[suffixes[0].index] = 0;

			// Assigning rank to suffixes
			for (int i = 1; i < n; i++) {
				// If first rank and next ranks are same as that of previous
				// suffix in array, assign the same new rank to this suffix
				if (suffixes[i].rank[0] == prev_rank && suffixes[i].rank[1] == suffixes[i - 1].rank[1]) {
					prev_rank = suffixes[i].rank[0];
					suffixes[i].rank[0] = rank;
				} else // Otherwise increment rank and assign
				{
					prev_rank = suffixes[i].rank[0];
					suffixes[i].rank[0] = ++rank;
				}
				ind[suffixes[i].index] = i;
			}

			// Assign next rank to every suffix
			for (int i = 0; i < n; i++) {
				int nextindex = suffixes[i].index + k / 2;
				suffixes[i].rank[1] = (nextindex < n) ? suffixes[ind[nextindex]].rank[0] : -1;
			}

			// Sort the suffixes
			Arrays.sort(suffixes);
		}

		// Store indexes of all sorted suffixes in the suffix array
		int[] suffixArr = new int[n];
		for (int i = 0; i < n; i++) {
			suffixArr[i] = suffixes[i].index;
		}

		return suffixArr;
	}

	public static void main(String args[]) {
		String str = "banana";
		int[] suffixArr = getSuffixArray(str);
		for (int i = 0; i < str.length(); i++) {
			System.out.println(suffixArr[i]);
		}
	}
}
