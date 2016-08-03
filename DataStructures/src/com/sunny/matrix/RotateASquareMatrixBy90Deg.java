package com.sunny.matrix;

public class RotateASquareMatrixBy90Deg {

	// Rotate a square matrix in place by 90 degree
	public static void main(String args[]) {
		int mat[][] = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

		int N = mat.length;
		for (int i = 0; i < N / 2; i++) {
			for (int j = i; j < N - i - 1; j++) {
				int temp = mat[i][j];

				// move values from right to top
				mat[i][j] = mat[j][N - 1 - i];

				// move values from bottom to right
				mat[j][N - 1 - i] = mat[N - 1 - i][N - 1 - j];

				// move values from left to bottom
				mat[N - 1 - i][N - 1 - j] = mat[N - 1 - j][i];

				// assign temp to left
				mat[N - 1 - j][i] = temp;
			}
		}

		for (int i = 0; i < N; i++) {
			String str = "";
			for (int j = 0; j < N; j++) {
				str = str + mat[i][j] + " ";
			}
			System.out.println(str);
		}
	}
}
