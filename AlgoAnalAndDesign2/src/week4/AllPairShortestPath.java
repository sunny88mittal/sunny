package week4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AllPairShortestPath {
	
	public static void main (String args[]) throws IOException {
		List<List<Edge>> graph = readFile("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 2\\Week 4\\Assignment\\g1.txt");
		int size = graph.size();
		
	}
	
	private static class Edge {
		int endPoint;
		int weight;
		
		Edge (int endPoint, int weight) {
			this.endPoint = endPoint;
			this.weight = weight;
		}
	}
	
	public static List<List<Edge>> readFile (String fileLocation) throws IOException {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileLocation));
			String line = in.readLine();
			String[] tokens = line.split(" ");
			int vertices = Integer.parseInt(tokens[0]);
			List<List<Edge>> graph = new ArrayList<List<Edge>>(vertices+1);
			graph.add(null);
			for (int i=1; i<=vertices;i++) {
				graph.add(new ArrayList<Edge>());
			}
			line = in.readLine();
			while (line != null) {
				tokens = line.split(" ");
				int startPoint = Integer.parseInt(tokens[0]);
				int endPoint = Integer.parseInt(tokens[1]);
				int weight = Integer.parseInt(tokens[2]);
				
				List<Edge> edgeList = graph.get(startPoint);
				edgeList.add(new Edge(endPoint, weight));
				
				edgeList = graph.get(endPoint);
				edgeList.add(new Edge(startPoint, weight));
				
				line = in.readLine();
			}
			return graph;	
		} finally {
		    if (in != null) {
		    	in.close();
		    }
		}
	}
}
