package com.sunny.stack;

import java.util.LinkedList;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/design-a-stack-with-find-middle-operation/
 * @author sumittal
 *
 */
public class StackWithOperationOnMidleElement {
	
	private static class SpecialStack {
	
		private List<Node> dobulyLinkedList = new LinkedList<Node>();
		
		private Node middleNode;
		
		public void push(int element){
			Node node = new Node();
			node.element = element;
			if (dobulyLinkedList.size() != 0) {
				node.previousElement = null;
				Node previousTop = dobulyLinkedList.get(0);
				node.nextElement = previousTop;
				previousTop.previousElement = node;
			}
			dobulyLinkedList.add(0,node);
			if (dobulyLinkedList.size() == 1) {
				middleNode = node;
			} else if (dobulyLinkedList.size() %2 != 0) {
				middleNode = middleNode.previousElement;
			}
		}
		
		public int pop() {
			Node node = dobulyLinkedList.remove(0);
			if (dobulyLinkedList.size() == 0) {
				middleNode = null;
			} else {
				//Mark the element above the new top as null
				dobulyLinkedList.get(0).previousElement = null;	
			}
			
			if(dobulyLinkedList.size() %2 == 0)  {
				middleNode = middleNode.nextElement;
			}	
			
			return node.element;
		}
		
		public int getMiddle() {
			return middleNode.element;
		}
		
		public void deleteMiddle(){
			dobulyLinkedList.remove(middleNode);
			if (dobulyLinkedList.size() != 0) {
				if (dobulyLinkedList.size() == 1) {
					Node currentTop = dobulyLinkedList.get(0);
					middleNode = currentTop;
					currentTop.previousElement = null;
				} else {
					//Adjust the doublylinked list
					middleNode.previousElement.nextElement = middleNode.nextElement;
					middleNode.nextElement.previousElement = middleNode.previousElement;
					//Select the new middle element
					if (dobulyLinkedList.size() %2 == 0){
						middleNode = middleNode.nextElement;
					} else {
						middleNode = middleNode.previousElement;	
					}
				}
			} else {
				middleNode = null;
			}
		}
		
		private static class Node {
			private int element;
			
			//Points to element below it in the stack
			private Node nextElement;
			
			//Points to element above it in the stack
			private Node previousElement;
		}
	}
	
	public static void main (String args[]) {
	    SpecialStack ss = new SpecialStack();
	    ss.push(11);
	    ss.push(22);
	    ss.push(33);
	    ss.push(44);
	    ss.push(55);
	    ss.push(66);
	    ss.push(77);
	    
	    System.out.println(ss.pop());
	    System.out.println(ss.pop());
	    System.out.println(ss.getMiddle());
	    
	    ss.deleteMiddle();
	    System.out.println(ss.getMiddle());
	    
	    ss.deleteMiddle();
	    System.out.println(ss.getMiddle());
	    
	    
	}
}
