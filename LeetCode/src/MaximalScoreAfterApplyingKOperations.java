import java.util.Collections;
import java.util.PriorityQueue;

public class MaximalScoreAfterApplyingKOperations {

	public long maxKelements(int[] nums, int k) {
		long sum = 0;
		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
		for (int i : nums) {
			queue.add(i);
		}
		while (k > 0) {
			double m = queue.poll();
			queue.add((int) Math.ceil(m / 3));
			sum += m;
			--k;
		}

		return sum;
	}
}
