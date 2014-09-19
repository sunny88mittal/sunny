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
public class InfixToPrefixExpr {
    
    public static void main (String args[]) {
        String expr = "( ( ( 8 + 1 ) - ( 7 - 4 ) ) / ( 11 - 9 ) )";
        String tokens[] = expr.split(" ");
        int count = tokens.length;
        List<String> stack =  new LinkedList<String>();
        List<String> resultStack = new LinkedList<String>();
        for (int i=0; i<count; i++) {
            String token = tokens[i];
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                if (stack.get(0).equals("(")) {
                    stack.add(0,token);
                } else {
                    resultStack.add(token);
                }
            } else if(token.equals(")")) {
                String stackTop = stack.get(0);
                if (stackTop.equals("+") || stackTop.equals("-") || stackTop.equals("*") || stackTop.equals("/")) {
                    resultStack.add(0,stack.remove(0));
                    stack.remove(0);
                } else {
                    String op2 = stack.remove(0);
                    String op1 = stack.remove(0);
                    stack.remove(0);
                    resultStack.add(op1);
                    resultStack.add(op2);    
                }
            }else {
                stack.add(0, token);
            } 
       }
       System.out.println(resultStack);
    }
}
