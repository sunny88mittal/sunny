package inmobi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ProjectTeam {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line);
		for (int i = 0; i < N; i++) {
			String[] tokens = br.readLine().split(" ");
			int n = Integer.parseInt(tokens[0]);
			int[] elements = new int[n];
			for (int j = 0; j < n; j++) {
				elements[j] = Integer.parseInt(tokens[j + 1]);
			}
			Arrays.sort(elements);

			int maxSum = -1;
			int minSum = Integer.MAX_VALUE;

			for (int j = 0; j < n / 2; j++) {
				int sum = elements[j] + elements[n - 1 - j];
				if (sum > maxSum) {
					maxSum = sum;
				}

				if (sum < minSum) {
					minSum = sum;
				}
			}

			System.out.println(maxSum - minSum);
		}
	}
}
