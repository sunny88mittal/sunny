package week1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrimMSTVerticesInHeap {
	
	private static VertexMinHeap verticesMinHeap = new VertexMinHeap(500);
	
	private static List<List<Edge>> graph;
	
	public static void main (String args[]) throws IOException {
		graph = readFile("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 2\\Week 1\\Assignment\\edges.txt");
		int size = graph.size();
		
		List<Integer> inTree = new ArrayList<Integer>();
		boolean inTreeStatus[] = new boolean[size];
		
		inTree.add(1);
		inTreeStatus[1] = true;
		initializeMinHeap(1, size-1);
		int mstWeight = 0;
		
		while (inTree.size() != (size-1)) {
			HeapVertex minVertex = verticesMinHeap.getMin();
			verticesMinHeap.deleteMin();
			
			mstWeight += minVertex.minWeight;
			inTree.add(minVertex.vertex);
			inTreeStatus[minVertex.vertex] = true;
			System.out.println("Added in tree:" + minVertex.vertex);
			
			List<Edge> edgesList = graph.get(minVertex.vertex);
			
			for(Edge edge : edgesList) {
				int vertex = edge.endPoint;
				if (!inTreeStatus[vertex]) {
					int weight = edge.weight;
					HeapVertex vertexInHeap = verticesMinHeap.delete(vertex);
					if (vertexInHeap.minWeight > weight) {
						vertexInHeap.minWeight = weight;
					}
					verticesMinHeap.addElement(vertexInHeap);	
				}
			}
		}
		
		System.out.println("MST weight is:" + mstWeight);
	}
	
	
	private static void initializeMinHeap (int startVertex, int noOfVertices) {
		boolean inHeap[] = new boolean[noOfVertices+1];
		List<Edge> edgesList = graph.get(startVertex);
		
		//Put all vertices corresponding to start vertex in heap with key as weight of the edge
		//from startVertex to them
		for (Edge edge : edgesList) {
			verticesMinHeap.addElement(new HeapVertex(edge.endPoint, edge.weight));
			inHeap[edge.endPoint] = true;
		}
		
		//For all other vertices put them in the heap with the key as infinite weight
		for (int i=1; i<=noOfVertices; i++) {
			if (i!=startVertex && !inHeap[i]) {
				verticesMinHeap.addElement(new HeapVertex(i, Integer.MAX_VALUE));
			}
		}
	}
	
	private static class Edge {
		int endPoint;
		int weight;
		
		Edge (int endPoint, int weight) {
			this.endPoint = endPoint;
			this.weight = weight;
		}
		
		@Override
		public String toString() {
			String str = "(" + endPoint + "," + weight + ")";
			return str;
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
