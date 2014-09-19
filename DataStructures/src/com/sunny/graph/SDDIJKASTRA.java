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
 * 4. . (2). . 5. . . (7) . .6
 */

public class SDDIJKASTRA {
    
    public static final int INFINITE = Integer.MAX_VALUE; 
            
    public static void main (String args[]) {
        List<List<Node>> graph = getGraph();
        int length = graph.size();
        int[] parent = new int[length];
        int[] weight = new int[length];
        int[] state = new int[length];
        //0-->Univisited, 1-->Fringe, 2-->Path
        int currentVertex = 1;
        state[currentVertex] = 2;
        while (currentVertex != 6) {
            int minWeight = INFINITE;
            int minVertex = 0;
            List<Node> adj = graph.get(currentVertex);
            //Mark the Parent and distance for all the nodes from current parent
            for (Node node : adj) {
                int nvertex = node.target;
                int nweight = node.weight;
                if (state[nvertex] == 0) {
                    state[nvertex] = 1;
                    weight[nvertex] = nweight;
                    parent[nvertex] = currentVertex;
                    if (nweight < minWeight) {
                        minVertex = nvertex;
                        minWeight = nweight;
                    }
                } else if (state[nvertex] == 1 && (weight[nvertex] + nweight < minWeight)) {
                    weight[nvertex] = weight[nvertex] + nweight;
                    parent[nvertex] = currentVertex;
                    minWeight = weight[nvertex];
                    minVertex = nvertex;
                }
            }
            
            state[minVertex] = 2;
            currentVertex = minVertex;
        }
        
        int nParent = parent[6];
        System.out.println("Root is:");
        while (nParent != 0) {
            System.out.println(nParent);
            nParent = parent[nParent];
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
        row4.add(new Node(5,2));
        
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
