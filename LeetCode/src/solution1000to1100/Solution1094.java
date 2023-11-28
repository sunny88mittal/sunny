package solution1000to1100;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution1094 {
	public class StartComparator implements Comparator<int[]> {
		@Override
		public int compare(int[] o1, int[] o2) {
			if (o1[1] != o2[1]) {
				return Integer.compare(o1[1], o2[1]);
			} else {
				return Integer.compare(o1[2], o2[2]);
			}
		}
	}

	public class EndComparator implements Comparator<int[]> {
		@Override
		public int compare(int[] o1, int[] o2) {
			return Integer.compare(o1[2], o2[2]);
		}
	}

	public boolean carPooling(int[][] trips, int capacity) {
		StartComparator startComparator = new StartComparator();
		EndComparator endComparator = new EndComparator();
		Arrays.sort(trips, startComparator);
		Queue<int[]> dropQueue = new PriorityQueue<>(endComparator);

		int n = trips.length;
		int usedCapacity = trips[0][0];
		dropQueue.add(trips[0]);
		for (int i = 1; i < n; i++) {
			if (usedCapacity > capacity) {
				return false;
			}
			while (dropQueue.size() > 0 && dropQueue.peek()[2] <= trips[i][1]) {
				usedCapacity -= dropQueue.remove()[0];
			}
			usedCapacity += trips[i][0];
			dropQueue.add(trips[i]);
		}
		return usedCapacity <= capacity;
	}

	public static void main(String args[]) {
		Solution1094 obj = new Solution1094();
		int[][] trips = new int[][] { { 9, 3, 4 }, { 9, 1, 7 }, { 4, 2, 4 }, { 7, 4, 5 } };
		int capacity = 23;
		obj.carPooling(trips, capacity);
	}
}
