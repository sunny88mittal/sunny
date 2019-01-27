package algorithms.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class TestClass {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int testCases = Integer.parseInt(line);
		for (int i = 0; i < testCases; i++) {
			String[] tokens = br.readLine().split(" ");
			int N = Integer.parseInt(tokens[0]);
			int K = Integer.parseInt(tokens[1]);

			tokens = br.readLine().split(" ");
			int[] elements = new int[N];
			for (int k = 0; k < tokens.length; k++) {
				elements[k] = Integer.parseInt(tokens[k]);
			}
			
			int p=0;
			while (K >=0 && p<N) {
			    //Find min element in K size array
			    int minPos = p;
			    for (int j = p+1; j <=p+K && j<N; j++) {
			        if (elements[j] <= elements[minPos]) {
			            minPos = j;
			        }
			    }
			    
			    //Swap the elements from right to left
			    for (int m=minPos; m>p; m--) {
			        if (elements[m] != elements[m-1]) {
			            int temp = elements[m];
			            elements[m] = elements[m-1];
			            elements[m-1] = temp;
			            --K;
			        }
			    }
			    
			    //Increment p
			    ++p;
		   }
		   
		   //Print the final Array
		   for (p=0; p<elements.length; p++) {
		       System.out.print(elements[p] + " ");
		   }
		   System.out.println();
		}
	}
}