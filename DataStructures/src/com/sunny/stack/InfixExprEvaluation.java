/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sunny.stack;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sumittal
 */
public class InfixExprEvaluation {
    
    public static void main (String args[]) {
        String expression = "(((2 * 5) - (1 * 2)) / (17 - 13))";
        char tokens[] = expression.toCharArray();
        List<String> stack = new LinkedList<String>();
        int length = tokens.length;
        for (int i=0; i<length; i++) {
            char c = tokens[i];
            if (c == ')') {
                int op1 = getInteger(stack);
                char op = stack.remove(0).charAt(0);
                int op2 = getInteger(stack);
                stack.remove(0);
                String value;
                switch (op) {
                    case '+':
                       value = op2 + op1 + "";
                       stack.add(0,value);
                       break; 
                    case '-':
                       value = op2 - op1 + "";
                       stack.add(0,value);
                       break; 
                    case '*':
                       value = op2 * op1 + "";
                       stack.add(0,value);
                       break; 
                    case '/':
                       value = op2 / op1 + "";
                       stack.add(0,value);
                       break; 
                }
            } else if (c != ' '){
                stack.add(0, c + "");
            }
        }
        System.out.println(stack.get(0));
    }
    
    public static int getInteger(List<String> stack) {
        List<Integer> tempList = new LinkedList<Integer>();
        tempList.add(Integer.parseInt(stack.remove(0)));
        while (true) {
            try {
                 int temp = Integer.parseInt(stack.get(0));
                 stack.remove(0);
                 tempList.add(temp);
            } catch (NumberFormatException ex) {
                break;
            }
        }
        Collections.reverse(tempList);
        int finalNumber = tempList.remove(0);
        int length = tempList.size();
        for (int i=0; i<length; i++) {
            finalNumber = finalNumber * 10 + tempList.get(i);
        }
        return finalNumber;
    }
}
