package com.sunny.matrix;

//http://www.geeksforgeeks.org/count-zeros-in-a-row-wise-and-column-wise-sorted-matrix/
public class ZerosInSortedMatrix {

	public static void main(String[] args) {
		int[][] mat = new int[][] { { 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 1 }, { 0, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1 } };

		mat = new int[][] { { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 } };

		int M = mat.length;
		int N = mat[0].length;

		int i = 0, j = N - 1;
		int count = M * N;

		while (i < M && i >= 0 && j < N && j >= 0) {
			if (mat[i][j] == 1) {
				j = j - 1; // Done with this column
				count = count - (N - i);
			} else {
				i = i + 1; // Done with this row
			}
		}

		System.out.println(count);
	}
}
