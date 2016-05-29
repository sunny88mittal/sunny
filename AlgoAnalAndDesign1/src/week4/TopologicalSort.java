package week4;

import java.util.ArrayList;
import java.util.List;

public class TopologicalSort {
	
	private static List<Integer> sortedList = new ArrayList<Integer>();
	
	private static boolean status[] = new boolean [4];
	
	public static void main (String args[]) {
		List<List<Integer>> graph = getGraph();
		computeTopologicalSort(graph);
		for (int i=0; i<sortedList.size(); i++) {
			System.out.println(sortedList.get(i));
		}
	}
	
	private static void computeTopologicalSort (List<List<Integer>> graph) {
		for (int i=0; i<graph.size(); i++) {
			if (!status[i]) {
				modifiedDFS(graph, i);
			}
		}
	}
	
	private static void modifiedDFS (List<List<Integer>> graph, int v) {
		status[v] = true;
		List<Integer> edges = graph.get(v);
		for (Integer i : edges) {
			if (!status[i]) {
				modifiedDFS(graph, i);	
			}
		}
		sortedList.add(0, v);
	}
	
	private static List<List<Integer>> getGraph() {
		List<List<Integer>> graph = new ArrayList<List<Integer>>();
		//Node 0
		List<Integer> node0 = new ArrayList<Integer>();
		node0.add(1);
		node0.add(2);
		graph.add(node0);
		
		//Node1
		List<Integer> node1 = new ArrayList<Integer>();
		node1.add(3);
		graph.add(node1);
		
		//Node2
		List<Integer> node2 = new ArrayList<Integer>();
		node1.add(3);
		graph.add(node2);
		
		//Node3
		List<Integer> node3 = new ArrayList<Integer>();
		graph.add(node3);
		
		return graph;
	}

}
