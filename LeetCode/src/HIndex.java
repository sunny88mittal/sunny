import java.util.Arrays;

public class HIndex {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int hIndex = 0;
        int length = citations.length;
        int start = length/2;

        if (citations[start] <= length - start) {
            while (citations[start] <= length - start && start < length) {
                hIndex = citations[start];
                ++start;
            }
        } else {
            while (citations[start] <= length - start && start >=0) {
                hIndex = citations[start];
                --start;
            }
        }
        return hIndex;
    }
}