import java.util.Arrays;
import java.util.Comparator;

public class MergeIntervals {

	class CustomComparator implements Comparator<int[]> {

		@Override
		public int compare(int[] o1, int[] o2) {
			return Integer.compare(o1[0], o2[0]);
		}
	}

	public int[][] merge(int[][] intervals) {
		CustomComparator cc = new CustomComparator();
		Arrays.sort(intervals, cc);
		int[][] mergedIntervals = new int[intervals.length][2];
		int j = 0;
		mergedIntervals[0] = intervals[0];
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] <= mergedIntervals[j][1]) {
				mergedIntervals[j][1] = Math.max(mergedIntervals[j][1], intervals[i][1]);
			} else {
				++j;
				mergedIntervals[j] = intervals[i];
			}
		}

		return Arrays.copyOf(mergedIntervals, j + 1);
	}
}
