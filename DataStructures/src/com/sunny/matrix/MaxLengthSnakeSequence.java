package com.sunny.matrix;

import java.util.Scanner;

/**
 * http://www.geeksforgeeks.org/find-maximum-length-snake-sequence/
 * @author sunny
 *
 */
public class MaxLengthSnakeSequence {

	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[][] values = new int[n][n];
		for (int i=0; i<n; i++) {
			String str[] = scan.nextLine().split(" ");
			for (int j=0; j<n; j++) {
				values[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		int[][] dp = new int[n][n];
		for (int i=n-1; i>=0; i--) {
			for (int j=n-1; j>=0; j--) {
				
			}
		}
 	}
 }
