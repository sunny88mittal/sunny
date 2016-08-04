package com.sunny.matrix;

//http://www.geeksforgeeks.org/find-if-given-matrix-is-toeplitz-or-not/
public class ToeplitzMatrix {

	public static void main(String[] args) {
		int[][] mat = new int[][] { { 6, 7, 8 }, { 4, 6, 7 }, { 1, 4, 6 } };
		mat = new int[][] { { 6, 7, 8, 9 }, { 4, 6, 7, 8 }, { 1, 4, 6, 7 }, { 0, 1, 4, 6 }, { 2, 0, 1, 4 } };
		mat = new int[][] { { 6, 3, 8 }, { 4, 9, 7 }, { 1, 4, 6 } };

		boolean isToeplitz = true;
		int M = mat.length;
		int N = mat[0].length;

		for (int i = 0; i < N; i++) {
			if (!checkDiagonal(mat, 0, i)) {
				isToeplitz = false;
				break;
			}
		}

		if (isToeplitz) {
			for (int i = 1; i < M; i++) {
				if (!checkDiagonal(mat, i, 0)) {
					isToeplitz = false;
					break;
				}
			}
		}

		System.out.println(isToeplitz);
	}

	private static boolean checkDiagonal(int[][] mat, int i, int j) {
		int M = mat.length;
		int N = mat[0].length;
		int ele = mat[i][j];
		while (++i < M && ++j < N) {
			if (mat[i][j] != ele) {
				return false;
			}
		}
		return true;
	}
}
