import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int stone : stones) {
            queue.add(stone);
        }

        while (queue.size() >=2) {
            int s1 = queue.poll();
            int s2 = queue.poll();

            if (s1 - s2 > 0) {
                queue.add(s1 -s2);
            }
        }

        return queue.size() > 0 ? queue.poll() : 0;
    }
}
