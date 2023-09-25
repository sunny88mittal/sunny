public class SmallestSubstring2734 {
    public String smallestString(String s) {
        char[] res = s.toCharArray();
        int n = s.length();
        int i =0;
        while (i<n && res[i] == 'a') {
            ++i;
        }

        if (i == n) {
            res[n-1] = 'z';
        }

        while (i<n && res[i] != 'a') {
            res[i] -=1;
            ++i;
        }

        return new String(res);
    }
}
