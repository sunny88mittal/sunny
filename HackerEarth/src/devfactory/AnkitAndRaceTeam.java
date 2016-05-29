package devfactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

//http://www.hackerearth.com/devfactory-hiring-challenge/problems/
public class AnkitAndRaceTeam {

	public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        for (int i = 0; i < N; i++) {
            String[] tokens = br.readLine().split(" ");
            int n = Integer.parseInt(tokens[0]);
            int k = Integer.parseInt(tokens[1]);
            long [] strength = new long[n];
            String[] strStr = br.readLine().split(" ");
            for (int m=0; m<n; m++) {
            	strength[m] = Long.parseLong(strStr[m]);
            }
            Arrays.sort(strength); 
            
            /*long sum = 0;
            int iterations = n-k;
            n = n-1; 
            k = k-1;
            long nCr = nCr(n,k);
            for (int m=0; m<=iterations; m++) {
            	sum = sum + (strength[m] * nCr); //% 1000000007;
            	n = n-1;
            	if (n >= 0) {
            		nCr = (nCr * (n+1-k) / (n+1)); //% 1000000007;	
            	}
            }
            System.out.println(sum % 1000000007);*/
            
            BigInteger sum = new BigInteger("0");
            int iterations = n-k;
            n = n-1; 
            k = k-1;
            BigInteger nCr = nCr(n,k);
            for (int m=0; m<=iterations; m++) {
            	sum = sum.add(nCr.multiply(new BigInteger("" + strength[m])));
            	n = n-1;
            	if (n >= 0) {
            		nCr = nCr.multiply(new BigInteger(n+1-k + "")).divide(new BigInteger("" + (n +1)));	
            	}
            }
            System.out.println(sum.mod(new BigInteger("" + 1000000007)).longValue());
        }
    }
	
	public static BigInteger nCr(int N, int R) {
		if (R == 0 || N == R)
			return new BigInteger("1");

		BigInteger numerator = new BigInteger("1");
		for (int i = 0; i < R; i++) {
			numerator = numerator.multiply(new BigInteger(N - i + ""));
		}

		BigInteger denominator = new BigInteger("1");
		for (int i = 1; i <= R; i++) {
			denominator = denominator.multiply(new BigInteger(i + ""));
		}

		return numerator.divide(denominator);
	}
	
	/*private static long nCr(long n,long r) {
		if (r == 0 || n == r) {
			return 1;
		}
		long num = 1;
		long den = 1;
		for (int i=0; i<n-r; i++) {
			num = (num * (n-i)); //% 1000000007; 
			den = (den * (n-r-i)); //% 1000000007;
		}
		return num/den;
	}*/
}
