public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();
        int j =0;
        int charMacthed = 0;
        for (int i=0; i<sLength && j<tLength; i++) {
            if (s.charAt(i) == t.charAt(j)) {
                ++charMacthed;
            } else {
                --i;
            }
            ++j;
        }

        return charMacthed == sLength;
    }
}
