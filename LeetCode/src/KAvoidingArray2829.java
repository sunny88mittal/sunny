import java.util.HashSet;
import java.util.Set;

public class KAvoidingArray2829 {
    public int minimumSum(int n, int k) {
        int sum = 0;
        Set<Integer> exisitingNos = new HashSet<>();
        int counter =1;
        while (exisitingNos.size() < n) {
            if (counter >= k || !exisitingNos.contains(k - counter)) {
                exisitingNos.add(counter);
                sum += counter;
            }
            ++counter;
        }

        return sum;
    }
}
