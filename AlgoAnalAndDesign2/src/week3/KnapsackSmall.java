package week3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class KnapsackSmall {
	
	private static int size = 100;
	
	private static int kweight = 10000;
	
	private static int weight[] = new int[size+1];
	
	private static int value[] = new int[size+1];
	
	public static void main (String args[]) throws IOException {
		readKnapsack("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 2\\Week 3\\Assignment\\knapsack1.txt");
		int [][] solArr = new int[size+1][kweight+1];
		
		//Initialize
		for (int i=0; i<=kweight; i++) {
			solArr[0][i] = 0;
		}
		
		//DP algo to compute the solution
		for (int i=1; i<=size; i++) {
			for (int j=0; j<=kweight; j++) {
				solArr[i][j] = solArr[i-1][j];  //Case 1, item excluded
				if ((j - weight[i]) > 0) {
					int optSol = solArr[i-1][j-weight[i]] + value[i]; //Case 2, Item included
					if (optSol > solArr[i][j]) {
						solArr[i][j] = optSol;
					}
				}
			}
		}
		
		System.out.println("Optimal Solution is:" + solArr[size][kweight]);
		
		/*int A[] = new int[kweight+1];
		int B[] = new int[kweight+1];
		
		//Initialize
		for (int i=0; i<=kweight; i++) {
		   A[i] = 0;
		}
		
		//DP algo to compute the solution
		for (int i=1; i<=size; i++) {
			for (int j=0; j<=kweight; j++) {
		        B[j] = A[j];
		        if ((j - weight[i]) > 0) {
		        	int optSol = value[i] + A[j-weight[i]];
		        	if (optSol > B[j]) {
		        		B[j] = optSol;
		        	}
		        }
			}
			A = B;
			B = new int[kweight+1];
		}
		
		System.out.println("Optimal Solution is:" +  + A[kweight]);*/
	}
	
	private static void readKnapsack (String filePath) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(filePath));
		String line = in.readLine();
		int i=1;
		while (line != null) {
			String[] tokens = line.split(" ");
			weight[i] = Integer.parseInt(tokens[1]);
			value[i] = Integer.parseInt(tokens[0]);
			i++;
			line = in.readLine();
		}
		in.close();
	}
}
