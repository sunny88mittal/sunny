package solution200to300;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution239 {

	// Time Complexity 2*n*log(k). If k is large it will not work
	// So it has to be done in O(n) time complexity
	/*
	 * public int[] maxSlidingWindow(int[] nums, int k) { int n = nums.length; int[]
	 * result = new int[n - k + 1]; Queue<Integer> queue = new
	 * PriorityQueue<>(Collections.reverseOrder());
	 * 
	 * for (int i = 0; i < k; i++) { queue.add(nums[i]); }
	 * 
	 * for (int i = k; i < n; i++) { result[i - k] = queue.peek();
	 * queue.remove(nums[i - k]); queue.add(nums[i]); }
	 * 
	 * if (k < n) { result[n - k] = queue.peek(); } else { result[0] = queue.peek();
	 * }
	 * 
	 * return result; }
	 */

	// Using monotonic queue
	public int[] maxSlidingWindow(int[] nums, int k) {
	    int[] ans = new int[nums.length - k + 1];
	    Deque<Integer> dq = new ArrayDeque<>(); // max queue

	    for (int i = 0; i < nums.length; ++i) {
	      while (!dq.isEmpty() && dq.peekLast() < nums[i])
	        dq.pollLast();
	      dq.offerLast(nums[i]);
	      if (i >= k && nums[i - k] == dq.peekFirst()) // out of bound
	        dq.pollFirst();
	      if (i >= k - 1)
	        ans[i - k + 1] = dq.peekFirst();
	    }

	    return ans;
	  }

	public static void main(String args[]) {
		int nums[] = new int[] { 1, -1 };
		Solution239 obj = new Solution239();
		obj.maxSlidingWindow(nums, 1);
	}
}
