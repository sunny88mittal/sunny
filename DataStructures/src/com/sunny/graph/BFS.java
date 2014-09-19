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

/* The graph is
 * 1. . . . . .2. . . . . . .3
 * .         . .           . .
 * .       .   .         .   .
 * .     .     .      .      .
 * .   .       .    .        .
 * . .         .  .          .
 * 4. . . . . .5. . . . . . .6
 */

/*
 * Expected output is 2 1 5 4 3 6 when starting from 2
 */

public class BFS {
    
    public static void main (String args[]) {
        List<List<Integer>> graph = getGraph();
        int length = graph.size();
        //States : 0--> Unexplored, 1-->Visited, 2-->Explored
        int[] state = new int[length+1];
        
        List<Integer> queue = new LinkedList<Integer>();
        List<Integer> ajacency2 = graph.get(1);
        for (int a: ajacency2) {
           state[a] = 1; 
           queue.add(a);
        }
        state[2] = 2;
        System.out.print(2 + "   ");
        while (!queue.isEmpty()) {    
            int element = queue.remove(0);
            System.out.print(element + "   ");
            state[element] = 2;
            List<Integer> adjList = graph.get(element -1);
            for (int a : adjList) {
                if (state[a] == 0) {
                    state[a] = 1; 
                    queue.add(a);
                }
            }
        }
    }
    
    public static List<List<Integer>> getGraph() {
        List<List<Integer>> graph = new LinkedList<List<Integer>>();
        
        List<Integer> row1 = new LinkedList<Integer>();
        row1.add(4);
        row1.add(2);
        
        List<Integer> row2 = new LinkedList<Integer>();
        row2.add(1);
        row2.add(5);
        row2.add(4);
        row2.add(3);
        
        List<Integer> row3 = new LinkedList<Integer>();
        row3.add(2);
        row3.add(5);
        row3.add(6);
        
        List<Integer> row4 = new LinkedList<Integer>();
        row4.add(1);
        row4.add(2);
        row4.add(5);
        
        List<Integer> row5 = new LinkedList<Integer>();
        row5.add(4);
        row5.add(2);
        row5.add(6);
        row5.add(3);
        
        List<Integer> row6 = new LinkedList<Integer>();
        row6.add(5);
        row6.add(3);
        
        graph.add(row1);
        graph.add(row2);
        graph.add(row3);
        graph.add(row4);
        graph.add(row5);
        graph.add(row6);
        
        return graph;
    }
}
