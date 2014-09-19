package com.sunny.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.sunny.unionfind.UnionFind;


/*
 * 1. . (5) . .2. . . (6) . .3
 * .         . .           . .
 * .       .   .         .   .
 *(4)   (6)   (2)     (7)   (5)
 * .   .       .    .        .
 * . .         .  .          .
 * 4. . (3). . 5. . . (7) . .6
 */

public class MSTKRUSKAL {
	
	public static void main (String args[]) {
		//Get the graph
		List<List<Edge>> graph = getGraph();
		
		//Sort all the nodes
		List<Edge> nodes = new ArrayList<Edge>();
		for (List<Edge> nodesList : graph) {
			nodes.addAll(nodesList);
		}
		Collections.sort(nodes);
		
		
		UnionFind unionFind = new UnionFind(graph.size() + 1);
		
		List<Edge> mstKruskal = new ArrayList<Edge>();
		for (Edge node : nodes) {
			if (!unionFind.connected(node.source, node.target)) {
				unionFind.union(node.source, node.target);
				mstKruskal.add(node);
			}
		}
		
		System.out.println(mstKruskal);
	}
	
	
	private static class Edge implements Comparable<Edge> {
        public int target;
        public int source;
        public int weight;
        
        public Edge(int source, int target, int weight) {
            this.source = source;
        	this.target = target;
            this.weight = weight;
        }

		@Override
		public int compareTo(Edge o) {
		    if (weight < o.weight) {
		    	return -1;
		    } else if (weight > o.weight) {
		    	return 1;
		    } 
		    return 0;
		}
		
		public String toString() {
			return "(" + source + "," + target + "," + weight + ")"; 
		}
    }
	
	private static List<List<Edge>> getGraph() {
        List<List<Edge>> graph = new LinkedList<List<Edge>>();
        
        List<Edge> row1 = new LinkedList<Edge>();
        row1.add(new Edge(1,2,5));
        row1.add(new Edge(1,4,4));
        
        List<Edge> row2 = new LinkedList<Edge>();
        row2.add(new Edge(2,1,5));
        row2.add(new Edge(2,5,2));
        row2.add(new Edge(2,4,6));
        row2.add(new Edge(2,3,6));
        
        List<Edge> row3 = new LinkedList<Edge>();
        row3.add(new Edge(3,2,6));
        row3.add(new Edge(3,5,7));
        row3.add(new Edge(3,6,5));
        
        List<Edge> row4 = new LinkedList<Edge>();
        row4.add(new Edge(4,1,4));
        row4.add(new Edge(4,2,6));
        row4.add(new Edge(4,5,3));
        
        List<Edge> row5 = new LinkedList<Edge>();
        row5.add(new Edge(5,4,3));
        row5.add(new Edge(5,2,2));
        row5.add(new Edge(5,6,7));
        row5.add(new Edge(5,3,7));
        
        List<Edge> row6 = new LinkedList<Edge>();
        row6.add(new Edge(6,5,7));
        row6.add(new Edge(6,3,5));
        
        graph.add(row1);
        graph.add(row2);
        graph.add(row3);
        graph.add(row4);
        graph.add(row5);
        graph.add(row6);
        
        return graph;
    }

}
