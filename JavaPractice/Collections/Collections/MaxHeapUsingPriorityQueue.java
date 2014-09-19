package Collections;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaxHeapUsingPriorityQueue {

	public static void main (String args[]) {
		//Reverses the comparison order
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2) * -1;
			}
		};
		
		Queue<Integer> minHeap = new PriorityQueue<>(comparator);
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
