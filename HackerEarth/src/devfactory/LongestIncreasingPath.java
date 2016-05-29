package devfactory;


//http://www.hackerearth.com/devfactory-hiring-challenge/problems/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LongestIncreasingPath {

	private static int rows = 0;
	
	private static int columns = 0;
	
	public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        for (int i = 0; i < N; i++) {
            String[] tokens = br.readLine().split(" ");
            rows = Integer.parseInt(tokens[0]);
            columns = Integer.parseInt(tokens[1]);
            int [][] matrix =  new int[rows][columns];
            for (int j=0; j<rows; j++) {
            	tokens = br.readLine().split(" ");
            	for (int k=0; k<columns; k++) {
            		matrix[j][k] = Integer.parseInt(tokens[k]);
            	}
            }
            System.out.println(getLongestPath(matrix, 0, 0) + 1);
        }
    }
	
	private static int getLongestPath (int[][] matrix, int i, int j) {
		int rightMax = 0;
		if (i < (rows - 1) && matrix[i+1][j] > matrix[i][j]) {
			rightMax = 1 + getLongestPath(matrix, i+1, j);
		}
		
		int downMax = 0;
		if (rightMax < (rows + columns - i -j -2)) {
			if (j < (columns - 1) && matrix[i][j+1] > matrix[i][j]) {
				downMax = 1 + getLongestPath(matrix, i, j+1);
			}
		}
	
		return Math.max(rightMax, downMax);
	}
}
