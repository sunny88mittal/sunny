import java.util.Arrays;

public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        int cookiesUsed = 0;
        int satisfied = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        for (int i=0 ;i<g.length ;i ++) {
            for (int j=cookiesUsed; j<s.length; j++) {
                if (g[i] <= s[j]) {
                    ++cookiesUsed;
                    ++satisfied;
                    break;
                } else {
                    ++cookiesUsed;
                }
            }
        }

        return satisfied;
    }
}
