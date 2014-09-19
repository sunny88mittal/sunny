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

//Dynamic Programming

public class SDBAPFLOYDWARSHALL {
    
    private static int INFINITY = Integer.MAX_VALUE / 2;
    
    public static void main (String args[]) {
        List<List<Node>> graph = getGraph();
        int length = graph.size();
        
        int[][] dist = new int[length][length];
        
        //Mark all the distances to be infinite and 0 if source and dest are same
        for (int i=1; i<length; i++) {
            for (int j=1; j<length; j++) {
                if (i==j) {
                    dist[i][j] = 0;   
                } else {
                    dist[i][j] = INFINITY;    
                }
            }
        }
        
        //Mark distances for the edges
        for (int i=1; i<length; i++) {
            List<Node> adj = graph.get(i);
            for(Node node: adj) {
                int nTarget = node.target;
                int nweight = node.weight;
                dist[i][nTarget] = nweight;  
            }
        }
        
        //Mark the optimal distances
        for (int k=1; k<length; k++) {
            for (int i=1; i<length; i++) {
                for (int j=1; j<length; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j] ){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        //Print the distances
        for(int i=1; i<length; i++){
            for (int j=1; j<length; j++) {
                if (i!=j) {
                    System.out.println("Distance from" + i + " to " + j + "is:" + dist[i][j]);
                }
            }
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
        row5.add(new Node(4,2));
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
