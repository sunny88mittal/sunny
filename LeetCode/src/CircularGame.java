import java.util.LinkedList;
import java.util.Queue;

public class CircularGame {
    public int findTheWinner(int n, int k) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i=1; i<=n ;i++) {
            queue.add(i);
        }
        
        while (queue.size() > 1) {
            int p = k;
            while (p > 1) {
                int element = queue.remove();
                queue.add(element);
                --p;
            }

            if (p == 1) {
                queue.remove();
            }
        }

        return queue.remove();
    }
}
