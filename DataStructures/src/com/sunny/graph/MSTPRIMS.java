/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sunny.graph;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sumittal
 */

/*
 * 1. . (5) . .2. . . (6) . .3
 * .         . .           . .
 * .       .   .         .   .
 *(4)   (6)   (2)     (7)   (5)
 * .   .       .    .        .
 * . .         .  .          .
 * 4. . (3). . 5. . . (7) . .6
 */

//Greedy Algorithm

public class MSTPRIMS {
    
    private static int INFINITE = Integer.MAX_VALUE;
    
    private static class Node {
        public int target;
        public int weight;
        
        public Node(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }
    
    public static void main (String args[]) {
        List<List<Node>> graph = getGraph();
        int length = graph.size();
        List<Integer> tree = new LinkedList<Integer>();
        List<Integer> fringeVertices = new LinkedList<Integer>();
        int parent[] = new int[length];
        int weight[] = new int[length];
        int state[] = new int[length];
        //0--> Unvisited 1--> Fringe, 2--> In tree 
        for (int i=0; i<length; i++) {
            weight[i] = INFINITE;
        }
        
        tree.add(1);
        int currentTreeVertex = 1;
        state[1] = 2;
        int treeSize = length-1;
        while (tree.size() < treeSize) {
            List<Node> nodeAdj = graph.get(currentTreeVertex); 
            //Assign the parents and the min weightts
            for (Node node : nodeAdj) {
                int nweight = node.weight;
                int vertex = node.target;
                if (state[vertex] == 0) {
                     state[vertex] = 1;
                     parent[vertex] = currentTreeVertex;
                     weight[vertex] = nweight;
                     fringeVertices.add(0,vertex);
                } else if (state[vertex] == 1 && nweight < weight[vertex] ) {
                     parent[vertex] = currentTreeVertex;
                     weight[vertex] = nweight;
                }
            }
            
            //Find the vertex with minimum wieght and add that to the tree
            currentTreeVertex = fringeVertices.get(0);
            int minWeight = weight[currentTreeVertex];
            for (int vertex : fringeVertices) {
                int nWeight = weight[vertex];
                if (nWeight < minWeight) {
                    currentTreeVertex = vertex;
                    minWeight = nWeight;
                }
            }
            
            fringeVertices.remove(new Integer(currentTreeVertex));
            state[currentTreeVertex] = 2;
            tree.add(currentTreeVertex);
        }
        
        for (int i=2; i<length; i++) {
            System.out.println("Parent for:" + i + " is" + parent[i]);
        }
    }
    
    private static List<List<Node>> getGraph() {
        List<List<Node>> graph = new LinkedList<List<Node>>();
        
        List<Node> row1 = new LinkedList<Node>();
        row1.add(new Node(2,5));
        row1.add(new Node(4,4));
        
        List<Node> row2 = new LinkedList<Node>();
        row2.add(new Node(1,5));
        row2.add(new Node(5,2));
        row2.add(new Node(4,6));
        row2.add(new Node(3,6));
        
        List<Node> row3 = new LinkedList<Node>();
        row3.add(new Node(2,6));
        row3.add(new Node(5,7));
        row3.add(new Node(6,5));
        
        List<Node> row4 = new LinkedList<Node>();
        row4.add(new Node(1,4));
        row4.add(new Node(2,6));
        row4.add(new Node(5,3));
        
        List<Node> row5 = new LinkedList<Node>();
        row5.add(new Node(4,3));
        row5.add(new Node(2,2));
        row5.add(new Node(6,7));
        row5.add(new Node(3,7));
        
        List<Node> row6 = new LinkedList<Node>();
        row6.add(new Node(5,7));
        row6.add(new Node(3,5));
        
        graph.add(null);
        graph.add(row1);
        graph.add(row2);
        graph.add(row3);
        graph.add(row4);
        graph.add(row5);
        graph.add(row6);
        
        return graph;
    }
}
