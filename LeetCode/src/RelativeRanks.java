import java.security.cert.CollectionCertStoreParameters;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class RelativeRanks {
    public String[] findRelativeRanks(int[] score) {
        String[] result = new String[score.length];
        Map<Integer,Integer> scoreAndLocation = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        int location = 0;
        for (int sc : score) {
            queue.add(sc);
            scoreAndLocation.put(sc, location);
            ++location;
        }

        int counter = 1;
        while (!queue.isEmpty()) {
            int sc = queue.poll();
            int loc = scoreAndLocation.get(sc);

            if (counter == 1) {
                result[loc] = "Gold Medal";
            } else if (counter == 2) {
                result[loc] = "Silver Medal";
            } else if (counter == 3) {
                result[loc] = "Bronze Medal";
            } else {
                result[loc] = counter + "";
            }

            ++counter;
        }

        return result;
    }
}
