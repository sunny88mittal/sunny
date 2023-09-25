public class SplitAStringInBalancedStrings {
    public int balancedStringSplit(String s) {
        int l = 0;
        int r = 0;
        int count = 0;

        for (char ch : s.toCharArray()) {
            if (ch == 'L') {
                ++l;
            }

            if (ch == 'R') {
                ++r;
            }

            if (l >= 1 &&  l == r) {
                ++count;
                l = 0;
                r = 0;
            }
         } 

        return count;
    }
}
