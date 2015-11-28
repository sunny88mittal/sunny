package SRM650;

import java.util.HashSet;
import java.util.Set;

public class TaroJiroDividing {

	public int getNumber(int A, int B) {
		int result = 0;
		Set<Integer> numbs = new HashSet<Integer>();
		numbs.add(A);
		while (A %2 == 0) {
			A = A/2;
			numbs.add(A);
		}
		
		if (numbs.contains(B)) {
			result++;
		}
		
		while (B%2 == 0) {
			B = B/2;
			if (numbs.contains(B)) {
				result++;
			}
		}
		
		return result;
	}
}
