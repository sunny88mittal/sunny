import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NetworkDelayTime {

	class GraphNode {
		int destination;
		int time;
	}

	public int networkDelayTime(int[][] times, int n, int k) {
		int signalRecievedAt[] = new int[n + 1];
		Arrays.fill(signalRecievedAt, Integer.MAX_VALUE);

		// Convert to adjacency list
		List<List<GraphNode>> adjacencyList = new ArrayList<List<GraphNode>>();
		for (int i = 0; i <= n; i++) {
			List<GraphNode> list = new ArrayList<>();
			adjacencyList.add(list);
		}

		for (int i = 0; i < times.length; i++) {
			int source = times[i][0];
			int destination = times[i][1];
			int time = times[i][2];

			GraphNode node = new GraphNode();
			node.destination = destination;
			node.time = time;

			List<GraphNode> list = adjacencyList.get(source);
			list.add(node);
		}

		// Do BFS and note the time
		Queue<Integer> queue = new LinkedList<>();
		queue.add(k);
		signalRecievedAt[k] = 0;
		while (!queue.isEmpty()) {
			int visiting = queue.poll();
			List<GraphNode> nodes = adjacencyList.get(visiting);
			for (GraphNode node : nodes) {
				int timeForSignal = signalRecievedAt[visiting] + node.time;
				if (signalRecievedAt[node.destination] > timeForSignal) {
					signalRecievedAt[node.destination] = timeForSignal;
					queue.add(node.destination);
				}
			}
		}

		// Check if all nodes received the signal and get max
		int minTime = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++) {
			if (i != k) {
				if (signalRecievedAt[i] == Integer.MAX_VALUE) {
					return -1;
				}

				if (signalRecievedAt[i] > minTime) {
					minTime = signalRecievedAt[i];
				}
			}
		}

		return minTime;
	}

	public static void main(String args[]) {
		int[][] times = new int[][] { { 2, 1, 1 }, { 2, 3, 1 }, { 3, 4, 1 } };
		NetworkDelayTime obj = new NetworkDelayTime();
		obj.networkDelayTime(times, 4, 2);
	}
}
