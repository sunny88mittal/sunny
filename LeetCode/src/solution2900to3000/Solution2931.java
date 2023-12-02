package solution2900to3000;

import java.util.PriorityQueue;

public class Solution2931 {
	public long maxSpending(int[][] values) {
		int m = values.length;
		int n = values[0].length;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				pq.add(values[i][j]);
			}
		}

		long res = 0;
		for (int i = 1; i <= m * n; i++) {
			res += (long) i * pq.remove();
		}

		return res;
	}
}
