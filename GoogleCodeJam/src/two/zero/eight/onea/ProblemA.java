package two.zero.eight.onea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class ProblemA {

	private static String smallFile = "/home/priyanka/Documents/source/sunny/GoogleCodeJam/src/two/zero/eight/onea/A-small-practice.in";

	private static String largeFile = "/home/priyanka/Documents/source/sunny/GoogleCodeJam/src/two/zero/eight/onea/A-large-practice.in";

	public static void main(String args[]) throws Exception {
		processFile(smallFile);
		processFile(largeFile);
	}

	private static void processFile(String input) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(input));
		PrintWriter pw = new PrintWriter(new FileWriter(input.replace("practice", "solution")));
		int testCases = Integer.parseInt(br.readLine());
		for (int i = 0; i < testCases; i++) {
            int n = Integer.parseInt(br.readLine());
            
            String[] a = br.readLine().split(" ");
            String[] b = br.readLine().split(" ");
            int ia[] = new int[n];
            int ib[] = new int[n];
            for (int j=0; j<n; j++) {
                ia[j] = Integer.parseInt(a[j]);
                ib[j] = Integer.parseInt(b[j]);
            }
            
            Arrays.sort(ia);
            Arrays.sort(ib);
            long product = 0;
            for (int j=0; j<n; j++) {
            	product += (long)ia[j] * (long)ib[n-1-j];
            }
            
            pw.println("Case #" + (i + 1) + ": " + product);
		}

		br.close();
		pw.flush();
		pw.close();
	}
}
