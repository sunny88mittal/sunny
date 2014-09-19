package Collections;

import java.util.PriorityQueue;
import java.util.Queue;

public class MinHeapUsingPriorityQueue {

	public static void main (String args[]) {
		Queue<Integer> minHeap = new PriorityQueue<>();
		minHeap.add(5);
		minHeap.add(1);
		minHeap.add(10);
		minHeap.add(3);
		
		System.out.println(minHeap.remove());
		System.out.println(minHeap.remove());
		System.out.println(minHeap.remove());
		System.out.println(minHeap.remove());
	}
}
