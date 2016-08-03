package com.sunny.matrix;

import java.util.Scanner;

public class LongestIsland {

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int m = Integer.parseInt(scan.nextLine());
		int n = Integer.parseInt(scan.nextLine());	
		
		int[][] grid = new int[m][n];
		for (int i=0; i<m; i++) {
			String[] tokens = scan.nextLine().split(" ");
			grid[i] = new int[n];
			for (int j = 0; j < n; j++) {
				grid[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		
		boolean[][] visited = new boolean[m][n];
		for (int i=0; i<m; i++) {
			visited[i] = new boolean[n];
		}
		
		int max = 0;
		for (int i=0; i<m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && grid[i][j] == 1) {
					int size = getSize(grid, visited, i, j, m ,n);
					if (size > max) {
						max = size;
					}
				}
			}
		}
		
		System.out.println(max);
	}
	
	private static int getSize(int[][] grid, boolean[][] visited, int r, int c, int m, int n) {
		int size = 0;
		int tr = -1;
		int tc = -1;
		for (int i=-1; i<=1; i++) {
			for (int j=-1; j<=1; j++) {
				tr = r + i;
				tc = c + j;
				if (tr >= 0 && tr < m && tc >= 0 && tc < n) {
					if (grid[tr][tc] == 1 && !visited[tr][tc]) {
						visited[tr][tc] = true;
						size = size + 1 + getSize(grid, visited, tr, tc, m ,n);
					}
				}
			}	
		}
		return size;
	}
}
