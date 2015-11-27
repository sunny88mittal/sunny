package SRM670;

import java.util.HashSet;
import java.util.Set;

/*Two Other Ways
 1. Generate all the combinations and take both the cases O(N^4). But ok as no of operations will be
 maximum 6250000
 2. Pre compute the sum of the arrays. Substract a[i], b[j] respectively. Than add b[j] and a[i] 
 respectively.
 */
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