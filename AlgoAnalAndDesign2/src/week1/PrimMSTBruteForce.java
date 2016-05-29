package week1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * 0. . .(1). . . .1
 * . .             .
 * .   .           .
 *(4)    (3)      (2)
 * .       .       .
 * .         .     .
 * .            .  .
 * 2. . . (5) . . .3
 */

public class PrimMSTBruteForce {
	
	public static void main (String args[]) throws IOException {
		List<List<Edge>> graph = readFile("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 2\\Week 1\\Assignment\\edges.txt");
		int size = graph.size();
		
		List<Integer> inTree = new ArrayList<Integer>();
		boolean inTreeStatus[] = new boolean[size];
		List<String> edges = new ArrayList<String>();
		
		inTree.add(1);
		inTreeStatus[1] = true;
		int mstWeight = 0;
		
		while (inTree.size() != (size-1)) {
		    int minWeight = Integer.MAX_VALUE;
		    String edgeToAdd = "";
		    int vertextToAdd = -1;
		    for (Integer vertex : inTree) {
		        if (inTreeStatus[vertex]) {
		        	List<Edge> edgesList = graph.get(vertex);
		        	for (Edge edge : edgesList) {
			        	int weight = edge.weight;
			        	int endPoint = edge.endPoint;
			        	if (!inTreeStatus[endPoint] && weight < minWeight) {
			        		minWeight = weight;
			        		edgeToAdd = vertex + " , " + endPoint;
			        		vertextToAdd = endPoint;
			        	}
			        }	
		        }
		    }
		    if (vertextToAdd !=-1) {
		    	mstWeight += minWeight;
			    edges.add(edgeToAdd);
			    inTreeStatus[vertextToAdd] = true;
			    inTree.add(vertextToAdd);	
		    }
		}
		
		System.out.println("Prim's MST edges are:");
		for (String str : edges) {
			System.out.println(str);
		}
		
		System.out.println("MST weight is:" + mstWeight);
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
		List<List<Edge>> graph = new ArrayList<List<Edge>>(501);
		graph.add(null);
		for (int i=1; i<=500;i++) {
			graph.add(new ArrayList<Edge>());
		}
		
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileLocation));
			String line = in.readLine();
			while (line != null) {
				String[] tokens = line.split(" ");
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
