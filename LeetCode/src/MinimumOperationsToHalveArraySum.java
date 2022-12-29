import java.util.Collections;
import java.util.PriorityQueue;

public class MinimumOperationsToHalveArraySum {

	public int halveArray(int[] nums) {
		int minOperations = 0;
		PriorityQueue<Double> queue = new PriorityQueue<>(Collections.reverseOrder());
		double sum = 0;
		for (int i = 0; i < nums.length; i++) {
			queue.add((double) nums[i]);
			sum += nums[i];
		}

		double reqSum = sum / 2;
		while (sum > reqSum) {
			double element = queue.poll();
			sum -= element / 2;
			queue.add(element / 2);
			++minOperations;
		}

		return minOperations;
	}
}
