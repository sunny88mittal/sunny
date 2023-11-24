package solution800to900;

import java.util.ArrayList;
import java.util.List;

public class Solution802 {
	// Time limit exceeded for larger test cases
	/*public List<Integer> eventualSafeNodes(int[][] graph) {
		List<Integer> temp = new ArrayList<>();
		List<Integer> result = new ArrayList<>();
		Map<Integer, Set<Integer>> edgesMap = new HashMap<>();
		for (int i = 0; i < graph.length; i++) {
			if (graph[i].length == 0) {
				temp.add(i);
				continue;
			}

			Set<Integer> edges = new HashSet<>();
			for (int e : graph[i]) {
				edges.add(e);
			}
			edgesMap.put(i, edges);
		}

		while (!temp.isEmpty()) {
			int node = temp.remove(0);
			result.add(node);
			Set<Integer> nodesProcessed = new HashSet<>();
			for (int key : edgesMap.keySet()) {
				Set<Integer> edges = edgesMap.get(key);
				edges.remove(node);
				if (edges.size() == 0) {
					temp.add(key);
					nodesProcessed.add(key);
				}
			}

			for (int key : nodesProcessed) {
				edgesMap.remove(key);
			}
		}

		Collections.sort(result);
		return result;
	}*/
	
	//Needed to get all nodes without cycle
	enum State {
		kInit, kVisiting, kVisited
	}

	public List<Integer> eventualSafeNodes(int[][] graph) {
		List<Integer> ans = new ArrayList<>();
		State[] states = new State[graph.length];

		for (int i = 0; i < graph.length; ++i)
			if (!hasCycle(graph, i, states))
				ans.add(i);

		return ans;
	}

	private boolean hasCycle(int[][] graph, int u, State[] states) {
		if (states[u] == State.kVisiting)
			return true;
		if (states[u] == State.kVisited)
			return false;

		states[u] = State.kVisiting;
		for (final int v : graph[u])
			if (hasCycle(graph, v, states))
				return true;
		states[u] = State.kVisited;

		return false;
 }
}
