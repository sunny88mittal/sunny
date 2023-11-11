package solution2500to2600;

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution2530 {
    public long maxKelements(int[] nums, int k) {
    	PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
    	for (int num : nums) {
    		pq.add((long) num);
    	}
    	
    	long sum = 0;
    	while (k > 0) {
    		long element  = pq.remove();
    		sum += element;
    		element = (long) Math.ceil(element/(double)3);
    		pq.add(element);
    		--k;
    	}
    	
    	return sum;
    }
}
