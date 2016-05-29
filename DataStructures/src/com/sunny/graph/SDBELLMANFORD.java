/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sunny.graph;

//http://www.geeksforgeeks.org/dynamic-programming-set-23-bellman-ford-algorithm/

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sumittal
 */
public class SDBELLMANFORD {
    
    public static final int INFINITE = Integer.MAX_VALUE / 3; 
            
    public static void main (String args[]) {
        List<List<Node>> graph = getGraph();
        int totalVertices = graph.size() - 1;
        int [] distances = new int[totalVertices + 1];
        distances[1] = 0; //Mark the distance from source to source as 0
        for (int i=2; i<= totalVertices; i++) {
        	distances[i] = INFINITE;  //Mark the distance to all other vertices as infinite
        }
        
        for (int i=0; i<(totalVertices-1); i++) { //Traverse for V-1 times
        	//Check all the edges and update the shortest path for all the vertices
        	for (int source=1; source<=totalVertices; source++) {
        		List<Node> nodes = graph.get(source);
        		for (Node node: nodes) {
        			int target = node.target;
        			int weight = node.weight;
        			if (distances[source] + weight < distances[target]) { //If the distance to a target vertex is shorter from the current vertex
        				distances[target] = distances[source] + weight;   //take that vertex
        			}
        		}
        	}
        }
        
        for (int i=1; i<=totalVertices; i++) {
        	System.out.println(distances[i]);
        }
    }
    
    private static class Node {
        public int target;
        public int weight;
        
        public Node(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }
   
    private static List<List<Node>> getGraph() {
        List<List<Node>> graph = new LinkedList<List<Node>>();
        
        List<Node> row1 = new LinkedList<Node>();
        row1.add(new Node(2,-1));
        row1.add(new Node(3,4));
        
        List<Node> row2 = new LinkedList<Node>();
        row2.add(new Node(3,3));
        row2.add(new Node(4,2));
        row2.add(new Node(5,2));
        
        List<Node> row3 = new LinkedList<Node>();
        
        List<Node> row4 = new LinkedList<Node>();
        row4.add(new Node(2,1));
        row4.add(new Node(3,5));
        
        List<Node> row5 = new LinkedList<Node>();
        row5.add(new Node(4,-3));
                
        graph.add(null);
        graph.add(row1);
        graph.add(row2);
        graph.add(row3);
        graph.add(row4);
        graph.add(row5);
        
        return graph;
    }
}
