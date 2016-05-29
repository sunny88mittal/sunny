package com.sunny.heap;

import org.testng.annotations.Test;

public class GenericMinHeapTests {
	@Test
	public void testAddElement() {
		GenericMinHeap<Integer> intHeap = new GenericMinHeap<Integer>();
		intHeap.addElement(10);
		assert intHeap.getHeapSize() == 1;
		assert intHeap.peekMin() == 10;
		
		intHeap.addElement(20);
		assert intHeap.getHeapSize() == 2;
		assert intHeap.peekMin() == 10;
		
		intHeap.addElement(25);
		assert intHeap.getHeapSize() == 3;
		assert intHeap.peekMin() == 10;
		
		intHeap.addElement(30);
		assert intHeap.getHeapSize() == 4;
		assert intHeap.peekMin() == 10;
		
		intHeap.addElement(32);
		assert intHeap.getHeapSize() == 5;
		assert intHeap.peekMin() == 10;
		
		intHeap.addElement(35);
		assert intHeap.getHeapSize() == 6;
		assert intHeap.peekMin() == 10;
		
		intHeap.addElement(37);
		assert intHeap.getHeapSize() == 7;
		assert intHeap.peekMin() == 10;
	}
	
	@Test
	public void testExtractMin() {
		GenericMinHeap<Integer> intHeap = new GenericMinHeap<Integer>();
		intHeap.addElement(10);
		intHeap.addElement(20);
		intHeap.addElement(35);
		intHeap.addElement(30);
		intHeap.addElement(25);
		intHeap.addElement(32);
		intHeap.addElement(37);	
		
		assert intHeap.extractMin() == 10;
		assert intHeap.getHeapSize() == 6;
		assert intHeap.peekMin() == 20;
		
		assert intHeap.extractMin() == 20;
		assert intHeap.getHeapSize() == 5;
		assert intHeap.peekMin() == 25;
		
		assert intHeap.extractMin() == 25;
		assert intHeap.getHeapSize() == 4;
		assert intHeap.peekMin() == 30;
		
		assert intHeap.extractMin() == 30;
		assert intHeap.getHeapSize() == 3;
		assert intHeap.peekMin() == 32;
		
		assert intHeap.extractMin() == 32;
		assert intHeap.getHeapSize() == 2;
		assert intHeap.peekMin() == 35;
		
		assert intHeap.extractMin() == 35;
		assert intHeap.getHeapSize() == 1;
		assert intHeap.peekMin() == 37;
		
		assert intHeap.extractMin() == 37;
		assert intHeap.getHeapSize() == 0;
		
	}
}
