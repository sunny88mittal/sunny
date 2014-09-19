package week5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Dijkastra {

	private static int size = 200;
	private static List<List<Edge>> graph = new ArrayList<List<Edge>>();
	
	private static int[] distance = new int[size+1];
	private static int[] state = new int [size+1];
	// 0--> Unvisited 1-->Visited
	
	public static void main (String args[]) throws IOException {	
		readGraph("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 1\\Week 5\\ProgrammingAssignment\\dijkstraData.txt");
		//readGraph("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 1\\Week 5\\ProgrammingAssignment\\Test.txt");
		//printGraph();
		SPDijkastra();
		//printAllDist();
		printMinDist();
	}
	
	
	private static void printAllDist() {
		for (int i=1; i<=size; i++) {
			System.out.println(distance[i]);
		}
	}
	
	private static void printMinDist() {
		System.out.println(distance[7]);
		System.out.println(distance[37]);
		System.out.println(distance[59]);
		System.out.println(distance[82]);
		System.out.println(distance[99]);
		System.out.println(distance[115]);
		System.out.println(distance[133]);
		System.out.println(distance[165]);
		System.out.println(distance[188]);
		System.out.println(distance[197]);
	}
	
	private static void SPDijkastra() {
		int currentVertex = 1;
		distance[1] = 0;
		state[1] = 1;
		
		for (int i=2; i<=size; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		while (currentVertex != 0) {
			//System.out.println(currentVertex);
			List<Edge> edges = graph.get(currentVertex);
			for (Edge edge: edges) {
				int targetVertex = edge.targetVertex;
				int weight = edge.weight;
				if (state[targetVertex] == 0) {
					if ((distance[currentVertex] +  weight) < distance[targetVertex] ) {
						distance[targetVertex] = distance[currentVertex] +  weight;
					}	
				}
			}
			
			state[currentVertex] = 1;
			currentVertex = getMinVertex();
		}
	}
	
	private static int getMinVertex() {
		int minVertex = 0;
		int minWeight = Integer.MAX_VALUE;
		for (int i=2; i<=size; i++) {
			if (state[i] == 0) {
				if (minVertex == 0) {
					minVertex = i;	
					minWeight = distance[i];
					continue;
				}
				
				if (distance[i] < minWeight) {
					minVertex = i;
					minWeight = distance[minVertex];
				}
			}
		}
		
		return minVertex;
	}
	
	private static void printGraph() {
		for (int i=1; i<=size; i++) {
			List<Edge> list = graph.get(i);
			System.out.println(i);
			for (Edge edge : list) {
				System.out.print("\t" + edge.targetVertex + "," +  edge.weight);
			}
			System.out.println();
		}
	}
	
	
	private static void readGraph(String file) throws IOException {
		BufferedReader in = null;
		graph.add(null);
		try {
			in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			while (line != null) {
				String[] tokens = line.trim().split("\t");
				int length = tokens.length;
				List<Edge> list = new ArrayList<Edge>();
				for (int i=1; i<length; i++) {
					String[] numbers  = tokens[i].split(",");
					int targetVertex = Integer.parseInt(numbers[0]);
					int weigth = Integer.parseInt(numbers[1]);
					list.add(new Edge(targetVertex, weigth));
				}
				graph.add(list);
				line = in.readLine();
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
