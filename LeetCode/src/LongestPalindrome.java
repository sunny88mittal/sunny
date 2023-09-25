import java.util.HashMap;
import java.util.Map;

public class LongestPalindrome {
    public int longestPalindrome(String s) {
        int largestPailndrome = 0;
        Map<Character, Integer> charCount = new HashMap<>();
        
        for (char ch : s.toCharArray()) {
            Integer count = charCount.get(ch); 
            if (count == null) {
                count = 0;
            }
            ++count;
            charCount.put(ch, count);
        }

        boolean oddValue = false;
        for (int value : charCount.values()) {
            if (value %2 == 0) {
                largestPailndrome += value;
            } else {
                largestPailndrome += value - 1;
                oddValue = true;
            }
        }

        if (oddValue) {
            largestPailndrome +=1;
        }
        return largestPailndrome;
    }
}
