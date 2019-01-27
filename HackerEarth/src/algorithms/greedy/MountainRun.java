package algorithms.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class MountainRun {
 
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int testCases = Integer.parseInt(line);
		for (int i = 0; i < testCases; i++) {
			String[] tokens = br.readLine().split(" ");
			int N = Integer.parseInt(tokens[0]);
			int M = Integer.parseInt(tokens[1]);
			char[] game = br.readLine().toCharArray();
	        int maxScore = 0;
			for (int j=0; j<N; j++) {
	            int startedAt = j;
	            int startedWith = M;
	            while (M >= 0 && j<N) {
	            	if (game[j] == '0') {
	            		--M;
	            	}
	            	++j;
	            }
	            int score = j - startedAt + M - startedWith;
	            maxScore = score > maxScore ? score : maxScore;
	            
	            while (startedAt < j) {
	            	if (game[startedAt] == '0') {
	            		++M;
	            	} else if (M >= 0) {
	            		break;
	            	}
	            	++startedAt;  
	            }
	            j = startedAt;
	        }		
			System.out.println(maxScore);
		}
	}
}