package nasdaqomxqa;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Blackbox1 {

	public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        for (int i = 0; i < N; i++) {
            String tokens[] = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            int c = Integer.parseInt(tokens[2]);
            
            if (gcd(a,b) != 1 || gcd(b,c) !=1 || gcd(c,a) !=1) {
            	System.out.println("NO");
            	continue;
            }
            
            long rhs = 0;
            if (a>b) {
            	rhs = (long)b*b;
            } else {
            	rhs = (long)a*a;
            	a = b; 
            }
            
            if (a>c) {
            	rhs += (long)c*c;
            } else {
            	rhs += (long)a*a;
            	a = c; 
            }
            
            if ((long)a*a == rhs) {
            	System.out.println("YES");
            } else {
            	System.out.println("NO");
            }
        }
    }
	
	private static int gcd(int a, int b) {
		int rem = b % a;
		while (rem !=0) {
			b = a;
			a = rem;
			rem = b % a;
		}
		return a;
	}
}
