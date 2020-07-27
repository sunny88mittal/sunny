package com.sunny.array;

/**
 * http://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-extra-space/
 * Given an array of n elements which contains elements from 0 to n-1, with any
 * of these numbers appearing any number of times. Find these repeating numbers
 * in O(n) and using only constant memory space. For example, let n be 7 and
 * array be {1, 2, 3, 1, 3, 6, 6}, the answer should be 1, 3 and 6.
 * 
 * @author sumittal
 *
 */
public class FindDuplicates {

	public static void main(String args[]) {
		int numbers[] = new int[] { 1, 2, 3, 1, 3, 6, 6 };
		int i = 0;
		while (i < numbers.length) {
			int num = numbers[i];

			if (num < 0) {
				++i;
				continue;
			}

			if (numbers[num] == -1) {
				numbers[num] = -2;
				System.out.println(num);
				++i;
			} else if (numbers[num] == i) {
				numbers[i] = -1;
				++i;
			} else {
				int temp = numbers[num];
				numbers[num] = -1;
				numbers[i] = temp;
			}
		}
	}
}
