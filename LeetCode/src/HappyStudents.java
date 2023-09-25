import java.util.Collections;
import java.util.List;

public class HappyStudents {
    public int countWays(List<Integer> nums) {
        if (nums.size() == 0) {
            return 0;
        }
        
        int ways = 0;
        Collections.sort(nums);
        int length = nums.size();

        int ss = 0;
        int ssv = -1;
        int nsv = nums.get(0);

        for (int i=0; i<length; i++) {
            if (ss > ssv && ss < nsv) {
                ++ways;
            }
            ++ss;
            ssv = nums.get(i);
            if (i+1 < length) {
                nsv = nums.get(i+1); 
            } else {
                nsv = Integer.MAX_VALUE;
            }
        }

        if (ss > ssv && ss < nsv) {
            ++ways;
        }

        return ways;
    }
}
