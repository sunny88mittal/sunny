package SRM624;

import java.util.Set;
import java.util.TreeSet;

public class GameOfSegments {

	public int winner(int N) {
		int[] nimber = new int[N+1];
		
		for (int n=0; n<=N; n++) {
			Set<Integer> options = new TreeSet<Integer>();
			for (int i=0; i<=n-2; i++) {
				options.add(nimber[i] ^ nimber[n-i-2]);
			}
			
			int r=0;
			while (options.contains(r)) {
				r++;
			}
			nimber[n]=r;
		}
		
		 return nimber[N] > 0 ? 1: 2;
	}
}
