package SRM653;

public class CountryGroup {

	public int solve(int[] a) {
		int result = 0;
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int beg = i;
			int end = beg + a[i] - 1;
			if (end >= n) {
				return -1;
			}
			for (int j = beg; j < n && j <= end; j++) {
				if (a[j] != a[i]) {
					return -1;
				}
			}
			i = end;
			result++;
		}
		return result;
	}
}
