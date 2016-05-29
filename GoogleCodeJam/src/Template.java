

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Template {

	private static String smallFile = "/home/priyanka/workspace/GoogleCodeJam/src/two/zero/eight/onea/A-small-practice.in";

	private static String largeFile = "/home/priyanka/workspace/GoogleCodeJam/src/two/zero/eight/onea/A-large-practice.in";

	public static void main(String args[]) throws Exception {
		processFile(smallFile);
		processFile(largeFile);
	}

	private static void processFile(String input) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(input));
		PrintWriter pw = new PrintWriter(new FileWriter(input.replace("practice", "solution")));
		int testCases = Integer.parseInt(br.readLine());
		for (int i = 0; i < testCases; i++) {
			pw.println("Case #" + (i + 1) + ": ");
		}

		br.close();
		pw.flush();
		pw.close();
	}
}
