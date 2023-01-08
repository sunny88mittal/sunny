import java.util.Collections;
import java.util.PriorityQueue;

public class SlidingWindowMaximum {

	public int[] maxSlidingWindow(int[] nums, int k) {
		int[] results = new int[nums.length - k + 1];
		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < k; i++) {
			queue.add(nums[i]);
		}

		for (int i = k; i < nums.length; i++) {
			results[i - k] = queue.peek();
			queue.remove(nums[i - k]);
			queue.add(nums[i]);
		}

		results[nums.length - k] = queue.peek();
		return results;
	}
}
