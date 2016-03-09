package two.zero.eight.qualification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProblemA {

	private static String smallFile = "/home/priyanka/workspace/GoogleCodeJam/src/two/zero/eight/qualification/A-small-practice.in";

	private static String largeFile = "/home/priyanka/workspace/GoogleCodeJam/src/two/zero/eight/qualification/A-large-practice.in";

	public static void main(String args[]) throws Exception {
		processFile(smallFile);
		processFile(largeFile);
	}

	private static void processFile(String input) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(input));
		PrintWriter pw = new PrintWriter(new FileWriter(input.replace("practice", "solution")));
		int testCases = Integer.parseInt(br.readLine());
		for (int i = 0; i < testCases; i++) {
			List<String> engines = new ArrayList<String>();
			List<String> queries = new ArrayList<String>();

			int sCount = Integer.parseInt(br.readLine());
			for (int j = 0; j < sCount; j++) {
				engines.add(br.readLine());
			}

			int qCount = Integer.parseInt(br.readLine());
			for (int j = 0; j < qCount; j++) {
				queries.add(br.readLine());
			}

			pw.println("Case #" + (i + 1) + ": " + countSwitches(engines, queries));
		}

		br.close();
		pw.flush();
		pw.close();
	}

	private static int countSwitches(List<String> engines, List<String> queries) {
		int count = 0;
		int n = engines.size();
		Set<String> currentSet = new HashSet<String>();
		for (String query : queries) {
			if (!currentSet.contains(query)) {
				if (currentSet.size() == n - 1) {
					count++;
					currentSet.clear();
				}
			}
			currentSet.add(query);
		}

		return count;
	}
}
