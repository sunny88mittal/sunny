package com.sunny.stack;

import java.util.LinkedList;
import java.util.List;

/**
 * Design a Data Structure SpecialStack that supports all the stack operations like push(), pop(), isEmpty(), isFull() 
 * and an additional operation getMin() which should return minimum element from the SpecialStack. 
 * All these operations of SpecialStack must be O(1). To implement SpecialStack, you should only use standard Stack 
 * data structure and no other data structure like arrays, list, .. etc. 
 * http://www.geeksforgeeks.org/design-and-implement-special-stack-data-structure/
 * @author sumittal
 *
 */

public class SpecialStackDataStructure {

	private static class Specialstack {
		private List<Integer> stack;
		
		private List<Integer> auxillaryStack;
		
		public Specialstack() {
			stack = new LinkedList<Integer>();
			auxillaryStack = new LinkedList<Integer>();
		}
		
		public void push(Integer element) {
			stack.add(0, element);
			if (auxillaryStack.size() == 0) {
				auxillaryStack.add(0,element);
			} else {
				//Keep the min of stack till each point in the auxillary stack
				int auxillaryStackTop = auxillaryStack.get(0);
				if (element < auxillaryStackTop) {
					auxillaryStack.add(0,element);
				} else {
					auxillaryStack.add(0, auxillaryStackTop);
				}
			}
		}
		
		public Integer pop() {
			auxillaryStack.remove(0);
			return stack.remove(0);
		}
		
		public Integer getMin() {
			return auxillaryStack.get(0);
		}
	}
	
	public static void main (String args[]) {
		Specialstack ss =  new Specialstack();
		ss.push(16);
		ss.push(15);
		ss.push(29);
		ss.push(19);
		ss.push(18);
		
		System.out.println("Min is:" + ss.getMin());		
		System.out.println("Pop element:" + ss.pop());

		System.out.println("Min is:" + ss.getMin());
		System.out.println("Pop element:" + ss.pop());
		
		System.out.println("Min is:" + ss.getMin());
		System.out.println("Pop element:" + ss.pop());
		
		System.out.println("Min is:" + ss.getMin());
		System.out.println("Pop element:" + ss.pop());
		
		System.out.println("Min is:" + ss.getMin());
		System.out.println("Pop element:" + ss.pop());
	}
}
