import java.util.Collections;
import java.util.PriorityQueue;

public class RemoveStonesToMinimizeTheTotal {

	public int minStoneSum(int[] piles, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
		int sum = 0;
		for (int pile : piles) {
			queue.add(pile);
			sum += pile;
		}

		while (k > 0) {
			int pile = queue.remove();
			int removed = Math.floorDiv(pile, 2);
			sum -= removed;
			queue.add(pile - removed);
			--k;
		}

		return sum;
	}
}
