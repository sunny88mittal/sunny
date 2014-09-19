package com.sunny.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/reverse-a-stack-using-recursion/
 * @author sumittal
 *
 */
public class ReverseStackUsingRecurssion {
	
	public static void main (String args[]) {
		List<Integer> stack  = new ArrayList<Integer>();
		stack.add(0,1);
		stack.add(0,2);
		stack.add(0,3);
		stack.add(0,4);
		
		System.out.println("Original stack is:" + stack);
		reverse(stack);
		System.out.println("Reverse stack is:" + stack);
	}
	
	private static void reverse(List<Integer> stack) {
		if (stack.size() == 0) {
			return;
		}
		
		int x = stack.remove(0);
		reverse(stack);
		pushRecursively(stack, x);
	}
	
	private static void pushRecursively(List<Integer> stack, int x) {
		if (stack.size() == 0) {
			stack.add(0, x);
			return;
		}
		
		//This code reverses the elements in the stack
		int temp = stack.remove(0);
		pushRecursively(stack, x);
		stack.add(0,temp);
	}
}
