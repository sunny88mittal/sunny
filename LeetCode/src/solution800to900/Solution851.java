package solution800to900;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution851 {

	// Solution using DFS and memoization
	public int[] loudAndRich(int[][] richer, int[] quiet) {
		int n = quiet.length;
		int ans[] = new int[n];
		Map<Integer, List<Integer>> adj = new HashMap<>();
		for (int i = 0; i < richer.length; i++) {
			int a = richer[i][0];
			int b = richer[i][1];

			List<Integer> adjList = adj.get(b);
			if (adjList == null) {
				adjList = new ArrayList<Integer>();
			}
			adjList.add(a);
			adj.put(b, adjList);
		}

		for (int i = 0; i < n; i++) {
			if (adj.get(i) == null) {
				ans[i] = i;
				continue;
			}
			ans[i] = getMinQuietness(adj, i, quiet, ans);
		}

		return ans;
	}

	private static int getMinQuietness(Map<Integer, List<Integer>> adj, int vertex, int quiet[], int[] answer) {
		if (answer[vertex] != 0) {
			return answer[vertex];
		}

		int minQuietness = vertex;
		if (adj.get(vertex) != null) {
			for (int e : adj.get(vertex)) {
				int newQuiteness = getMinQuietness(adj, e, quiet, answer);
				if (quiet[newQuiteness] < quiet[minQuietness]) {
					minQuietness = newQuiteness;
				}
			}
		}
		return minQuietness;
	}
}
