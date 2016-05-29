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
public class InfixToPostfixExpr {
    
    public static void main (String args[]) {
        String expr = "( ( ( 8 + 1 ) - ( 7 - 4 ) ) / ( 11 - 9 ) )";
        String tokens[] = expr.split(" ");
        int count = tokens.length;
        List<String> stack =  new LinkedList<String>();
        for (int i=0; i<count; i++) {
            String token = tokens[i];
            if (token.equals("(")) {
                stack.add(0, token);
            } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                stack.add(0, token);
            } else if (token.equals(")")) {
                System.out.print(stack.remove(0));
                stack.remove(0);
            } else {
                System.out.print(token);
            }
        }
    }
}
