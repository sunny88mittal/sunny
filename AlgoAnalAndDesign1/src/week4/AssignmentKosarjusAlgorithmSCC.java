package week4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssignmentKosarjusAlgorithmSCC {
	
	private static int vertices = 875714;
	private static List<AdjListNode> graph = new ArrayList<AdjListNode>();
	private static List<AdjListNode> revGraph = new ArrayList<AdjListNode>();
    private static List<Integer> dfsstack = new ArrayList<Integer>(); 
	
	public static void main (String args[]) throws IOException {
		createNodes(vertices, graph);
		createNodes(vertices, revGraph);
		
		//readGraph("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 1\\Week 4\\ProgrammingAssinment\\TestGraph.txt");
		readGraph("C:\\Personal Data\\Courses\\Computers\\Data Structures And Algorithms\\Stanford\\Algorithm Analysis and Design 1\\Week 4\\ProgrammingAssinment\\SCC.txt");
		
		/*System.out.println("Graph is:");
		printGraph(graph);
		
		System.out.println("Graph is:");
		printGraph(revGraph);*/
		
		List<Integer> sccs = getSCCsUsingKosarjusAlgo();
		Collections.sort(sccs);
		Collections.reverse(sccs);
		int i=0;
		for (Integer size : sccs) {
			System.out.println(size);
			i++;
			if (i==10) {
				break;
			}
		}
	}
	
	private static void createNodes (int length, List<AdjListNode> graph) {
		graph.add(null);
		for (int i=1; i<=length; i++){
			graph.add(new AdjListNode(i));
		}
	}

	
	private static List<Integer> getSCCsUsingKosarjusAlgo(){
		traverseUsingDFS();
		return reverseTraverseUsingStack();
	}
	
	
	/**********For Phase 1***********/
	private static void traverseUsingDFS() {
		int length = graph.size();
		for (int i=1; i<length; i++) {
			AdjListNode node = graph.get(i);
			if (!node.isExplored) {
				traverseRecursively(node);
			}
		}
	}
	
	private static void traverseRecursively(AdjListNode node) {
		node.isExplored = true;
		List<AdjListNode> endPoints = node.endPoints;
		for (AdjListNode adjListNode : endPoints) {
			if (!adjListNode.isExplored){
				traverseRecursively(adjListNode);
			}
		}
		dfsstack.add(0, node.vertexNo);
	}
	/********For Phase 1***************/
	
	/********For Phase 2***************/
	private static List<Integer> reverseTraverseUsingStack() {
	    List<Integer> sccSizes = new ArrayList<Integer>();
		int length = dfsstack.size();
		for (int i=0; i<length; i++){
			AdjListNode node = revGraph.get(dfsstack.get(i));
			if (!node.isExplored){
				int sccSize = getSCC(node);
				sccSizes.add(sccSize);
			}
		}
		return sccSizes;
	}
	
	
	private static int getSCC(AdjListNode node){
		node.isExplored = true;
		int size = 1;
		List<AdjListNode> endPoints = node.endPoints;
		for (AdjListNode adjListNode : endPoints) {
			if (!adjListNode.isExplored){
				size += getSCC(adjListNode);
			}
		}
		return size;
	}
	/********For Phase 2***************/

	
	private static void printGraph(List<AdjListNode> adjList) {
		for (AdjListNode adjListNode : adjList) {
			if (adjListNode != null) {
				System.out.println(adjListNode.vertexNo);
				for (AdjListNode endPoint : adjListNode.endPoints) {
					System.out.print("\t" + endPoint.vertexNo);
				}
				System.out.println();	
			}
		}
	}
	
	private static void readGraph(String file) throws IOException {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			while (line != null) {
				String[] numbers = line.split(" ");
				int start  = Integer.parseInt(numbers[0]);
				int end = Integer.parseInt(numbers[1]);
				
				graph.get(start).endPoints.add(graph.get(end));
				revGraph.get(end).endPoints.add(revGraph.get(start));
				
				line = in.readLine();
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
