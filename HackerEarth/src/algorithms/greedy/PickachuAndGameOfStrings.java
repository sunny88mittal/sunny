package algorithms.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PickachuAndGameOfStrings {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int length = Integer.parseInt(line);
		String S = br.readLine();
		String T = br.readLine();
		int sum = 0;
		for (int i = 0; i < length; i++) {
            int sC = 'Z' - S.charAt(i);
            int tC = 'Z' - T.charAt(i);
            int temp = Math.abs(sC/13 - tC/13);
            sC %= 13;
            tC %= 13;
            temp += Math.abs(sC - tC);
            sum += temp % 13;
		}
		System.out.println(sum);
	}
}
