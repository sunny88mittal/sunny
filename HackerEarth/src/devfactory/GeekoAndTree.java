package devfactory;

//http://www.hackerearth.com/devfactory-hiring-challenge/problems/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class GeekoAndTree {

	public static void main(String args[] ) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        for (int i = 0; i < N; i++) {
        	String[] tokens = br.readLine().split(" ");
        	int weirdness = 0;
        	BigInteger totalSum =  new BigInteger(tokens[0]).pow(Integer.parseInt(tokens[1]) + 1).subtract(new BigInteger("1"))
        			.divide(new BigInteger("" + (Integer.parseInt(tokens[0]) - 1)));
        	char[] charArray  = totalSum.toString().toCharArray();
        	for (int j=0; j<charArray.length; j++) {
        		weirdness += Integer.parseInt("" + charArray[j]);
        	}
        	System.out.println(weirdness);
        }
    }
}
