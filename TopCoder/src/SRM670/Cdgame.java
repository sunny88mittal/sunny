package SRM670;

import java.util.HashSet;
import java.util.Set;

public class Cdgame {
	public int rescount(int[] a, int[] b) {
		Set<Integer> set = new HashSet<Integer>();
		for (int pA = 0; pA < a.length; pA++) {
			for (int pB = 0; pB < b.length; pB++) {
				swap(a, b, pA, pB);
				set.add(getSum(a) * getSum(b));
				swap(a, b, pA, pB);
			}
		}
		return set.size();
	}

	public int getSum(int[] a) {
		int sum = 0;
		for (int i : a)
			sum += i;
		return sum;
	}

	public void swap(int[] a, int[] b, int i, int j) {
		int tmp = a[i];
		a[i] = b[j];
		b[j] = tmp;
	}

}