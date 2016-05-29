package com.sunny.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenericMinHeap<T extends Comparable<T>> {

	private List<T> elements = new ArrayList<T>();

	public int getHeapSize() {
		return elements.size();
	}

	/**
	 * Adds an element to the heap Steps 
	 * 1. Add element to the end of the heap
	 * 2. Bubble-up until the heap property is restored
	 * 
	 * @param element
	 */
	public void addElement(T element) {
		elements.add(element);
		int size = elements.size();
		int currentPos = size - 1;
		int parentPos = currentPos / 2;
		while (elements.get(parentPos).compareTo(elements.get(currentPos)) > 0) {
			Collections.swap(elements, currentPos, parentPos);
			currentPos = parentPos;
			parentPos = currentPos / 2;
		}
	}

	/**
	 * Returns the min element of the heap This method does not deletes the min
	 * element from the heap
	 * 
	 * @return min element
	 */
	public T peekMin() {
		return elements.get(0);
	}

	/**
	 * Returns the min element and delete it from the heap Steps 
	 * 1. Swap the root and the last leaf element 
	 * 2. Delete the last leaf 
	 * 3. Bubble down until the heap property is restored 
	 *     Possible Cases 
	 *     1. At some point we only have left child 
	 *     2. At some point we don't have any children
	 * @return min element
	 */
	public T extractMin() {
		int heapSize = elements.size();
		Collections.swap(elements, 0, heapSize - 1);
		T min = elements.remove(heapSize - 1);
		heapSize = heapSize - 1;

		int rootPos = 0;
		int leftChildPos = 1;
		int rightChildPos = 2;

		while (leftChildPos < heapSize) {
			T rootElement = elements.get(rootPos);
			T leftChildElement = elements.get(leftChildPos);
			int newRootPos = rootPos;
			if (rootElement.compareTo(leftChildElement) > 0) {
				rootElement = leftChildElement;
				newRootPos = leftChildPos;
			}
			
			if (rightChildPos < heapSize) {
				T rightChildElement = elements.get(rightChildPos);
				if (rootElement.compareTo(rightChildElement) > 0) {
					rootElement = rightChildElement;
					newRootPos = rightChildPos;
				}
			}

			//This means root is less than both left and right
			if (rootPos == newRootPos) {
				break;
			}
			Collections.swap(elements, rootPos, newRootPos);
			rootPos = newRootPos;
			leftChildPos = 2 * rootPos + 1;
			rightChildPos = 2 * rootPos + 2;
		}
		return min;
	}
}