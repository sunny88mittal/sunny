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

public class DFS {
    
    public static void main (String args[]) {
        List<List<Integer>> graph = getGraph();
        int length = graph.size();
        //States : 0--> Unexplored, 1-->Visited, 2-->Explored
        int[] state = new int[length+1];
        
        List<Integer> stack = new LinkedList<Integer>();
        stack.add(2);
        state[2] = 1;
        while (true) {
            int element = stack.get(0);
            List<Integer> adj = graph.get(element -1);
            boolean found = false; 
            for (int a: adj) {
                if (state[a] == 0) {
                    stack.add(0, a);
                    state[a] = 1;
                    found = true;
                    break;
                }
            }
            
            if (!found){
               break;     
            }
        }
        System.out.println(stack);
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
