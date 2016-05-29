package yantra;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TheBattle {
	
	public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        for (int i = 0; i < N; i++) {
            long n = Integer.parseInt(br.readLine());
            long answer = n * (n-1) / 2;
            System.out.println(answer % 1000000007);
        }
    }
}
