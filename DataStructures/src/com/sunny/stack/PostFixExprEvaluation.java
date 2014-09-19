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

public class PostFixExprEvaluation {
    
    public static void main (String args[]) {
        String expr = "1 4 2 - + 3 +";
        String[] tokens = expr.split(" ");
        List<Integer> stack = new LinkedList<Integer>();
        int length = tokens.length;
        for (int i=0; i<length; i++) {
            String token = tokens[i];
            try{
               int temp = Integer.parseInt(token);
               stack.add(0,temp);
               continue;
            } catch (NumberFormatException nfe) {
               //Do nothing   
            }
            char operator = token.charAt(0);
            int op2 = stack.remove(0);
            int op1 = stack.remove(0);
            switch (operator) {
                case '+':
                  stack.add(0, op1 + op2);
                  break;  
                case '-':
                  stack.add(0, op1 - op2);
                  break;  
                case '*':
                  stack.add(0, op1 * op2);
                  break;   
                case '/':
                  stack.add(0, op1 / op2);
                  break;    
            }
       }
       System.out.println(stack.get(0));
    }
}
