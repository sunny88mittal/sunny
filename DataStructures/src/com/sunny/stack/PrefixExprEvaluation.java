/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sunny.stack;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sumittal
 */

public class PrefixExprEvaluation {
    
    public static void main (String args[]) {
        String expr = "/ - * 2 5 * 1 2 - 11 9";
        String[] tokens = expr.split(" ");
        int length = tokens.length;
        
        String[] stack = new String[length];
        for (int i=0; i<length; i++) {
            stack[i] = tokens[length-i-1];
        }
        
        List<Integer> intStack = new LinkedList<Integer>();
        for (int i=0; i<length; i++) {
            try{
               int temp = Integer.parseInt(stack[i]);
               intStack.add(0, temp);
               continue;
            } catch (NumberFormatException nfe) {
               //Do nothing   
            }
            
            char operator = stack[i].charAt(0);
            int op1 = intStack.remove(0);
            int op2 = intStack.remove(0);
            switch (operator) {
                case '+':
                  intStack.add(0, op1 + op2);
                  break;  
                case '-':
                  intStack.add(0, op1 - op2);
                  break;  
                case '*':
                  intStack.add(0, op1 * op2);
                  break;   
                case '/':
                  intStack.add(0, op1 / op2);
                  break;    
            }
        }
        System.out.println(intStack.get(0));
    }
}
