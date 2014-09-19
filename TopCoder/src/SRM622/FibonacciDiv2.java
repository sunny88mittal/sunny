package SRM622;

public class FibonacciDiv2 {
	
	public 	int find(int N) {
		int fibi = 0;
		int fibi1 = 1;
		
		while (fibi1 < N) {
			int temp = fibi1;
			fibi1 = fibi1 + fibi;
			fibi = temp;
		}
		
		return Math.min(N-fibi, fibi1-N);
	}
}
