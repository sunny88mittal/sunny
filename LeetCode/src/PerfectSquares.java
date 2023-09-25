public class PerfectSquares {
    public int numSquares(int n) {
        int[] counter = new int[n+1];
        counter[1] = 1;
        for (int i=2; i<=n ;i++) {
            counter[i] = Integer.MAX_VALUE;
            int sqrt = (int)Math.sqrt(i);
            if (sqrt * sqrt == i) {
                counter[i] =1;
                continue;
            }

            for (int j=1; j<=i/2; i++) {
               counter[i] = Math.min(counter[i], counter[j] + counter[i-j]);
            }
        }

        return counter[n];
    }
}
