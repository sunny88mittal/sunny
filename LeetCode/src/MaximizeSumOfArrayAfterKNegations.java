import java.util.PriorityQueue;

public class MaximizeSumOfArrayAfterKNegations {
    public int largestSumAfterKNegations(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.add(num);
        }

        for (int i=0 ; i<k; i++) {
            int num = queue.poll();
            queue.add(-1 * num);
        }

        int sum= 0;
        while (!queue.isEmpty()) {
            sum += queue.poll();
        }

        return sum;
    }
}