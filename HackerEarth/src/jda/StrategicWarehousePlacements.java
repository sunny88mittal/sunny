package jda;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StrategicWarehousePlacements {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int conn = Integer.parseInt(tokens[1]);
		boolean[][] paths = new boolean[N + 1][N + 1];

		for (int i = 0; i < conn; i++) {
			tokens = br.readLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			paths[a][b] = true;
			paths[b][a] = true;
		}

		// Get the no.of connections to each city
		int[] count = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			count[i] = 1;
			for (int j = 1; j <= N; j++) {
				if (paths[i][j]) {
					count[i] += 1;
				}
			}
		}

		boolean isDone[] = new boolean[N + 1];
		int warehouses = 0;

		while (true) {
			int maxIndex = 0;
			for (int i = 1; i <= N; i++) {
				if (!isDone[i] && count[i] > count[maxIndex]) {
					maxIndex = i;
				}
			}

			if (maxIndex != 0) {
				isDone[maxIndex] = true;
				warehouses += 1;
				for (int i = 1; i <= N; i++) {
					if (paths[maxIndex][i] && !isDone[i]) {
						count[i] -= 1;
						if (count[i] == 1) {
							isDone[i] = true;
						}
					}
				}
			} else {
				break;
			}
		}

		// Print
		System.out.println(warehouses);
	}
}
