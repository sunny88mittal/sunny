package week3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class KnapsackLarge {
	
	private static int size = 2000;
	
	private static int kweight = 2000000;
	
	private static int weight[] = new int[size+1];
	
	private static int value[] = new int[size+1];
	
	public static void main (String args[]) throws IOException {
		readKnapsack("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 2\\Week 3\\Assignment\\knapsack_big.txt");
		
		int A[] = new int[kweight+1];
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
		
		System.out.println("Optimal Solution is:" + A[kweight]);
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
