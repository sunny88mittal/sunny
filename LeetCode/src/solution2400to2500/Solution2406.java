package solution2400to2500;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution2406 {
	public int minGroups(int[][] intervals) {
		// Stores the `right`s.
		Queue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);

		Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

		for (int[] interval : intervals) {
			if (!minHeap.isEmpty() && interval[0] > minHeap.peek())
				minHeap.poll(); // There is no overlaps, so we can reuse the same group.
			minHeap.offer(interval[1]);
		}

		return minHeap.size();
	}
}
