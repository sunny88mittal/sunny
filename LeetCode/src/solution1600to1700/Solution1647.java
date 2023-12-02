package solution1600to1700;

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution1647 {
	public int minDeletions(String s) {
		int count[] = new int[26];

		for (char ch : s.toCharArray()) {
			++count[ch - 'a'];
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int c : count) {
			if (c != 0) {
				pq.add(c);
			}
		}

		int minDeletions = 0;
		int startingCount = 0;
		while (!pq.isEmpty()) {
			if (startingCount == 0) {
				startingCount = pq.remove();
				continue;
			}

			if (pq.peek() != startingCount) {
				startingCount = pq.remove();
			} else {
				++minDeletions;
				pq.add(--startingCount);
				startingCount = 0;
			}
		}

		return minDeletions;
	}
}
