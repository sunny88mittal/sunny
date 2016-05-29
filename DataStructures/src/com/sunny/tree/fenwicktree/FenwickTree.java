package com.sunny.tree.fenwicktree;

public class FenwickTree {

	private Node[] tree;

	private class Node {
		int sum;
		Node parent;
	}

	public FenwickTree(int size) {
		tree = new Node[size + 1];
	}

	/**
	 * Mark the parents of all the nodes 
	 */
	private void createTreeSkelton() {
		int n = tree.length;
		for (int i = 0; i < n; i++) {
			tree[i] = new Node();
		}

		for (int i = 1; i < n; i++) {
			tree[i].parent = tree[getParent(i)];
		}
	}
	
	/**
	 * Fill the elements
	 * Add in the current position
	 * Add in the next positions
	 * @param elements
	 */
	private void fillElements(int[] elements) {
		createTreeSkelton();
		int n = elements.length;
		for (int i = 0; i < n; i++) {
			int j = i + 1;
			Node node = tree[j];
			node.sum += elements[i];
			while ((j = getNext(j)) <= n) {
				node = tree[j];
				node.sum += elements[i];
			}
		}
	}
	
	private int getSum(int end) {
		int startNode = end + 1;
		int sum = 0;
		while (startNode !=0 ) {
			sum += tree[startNode].sum;
			startNode = getParent(startNode);
		}
		return sum;
	}
	
	/**
	 * Get the parent for the current node
	 * 1. Takes 2's complement 
	 * 2. AND with the original number 
	 * 3. Subtract result from original number
	 * @param a
	 * @return
	 */
	private int getParent(int a) {
		int temp = a;
		temp = ~temp + 1;
		temp &= a;
		temp = a - temp;
		return temp;
	}
	
	/**
	 * Get the parent for the current node
	 * 1. Takes 2's complement 
	 * 2. AND with the original number 
	 * 3. ADD result with original number
	 * @param a
	 * @return
	 */
	private int getNext(int a) {
		int temp = a;
		temp = ~temp + 1;
		temp &= a;
		temp = a + temp;
		return temp;
	}
	
	public static void main (String args[]) {
		int[] arr = new int[] {2,3,4,5,-1,7,3,0,6,3,8};
		FenwickTree ft = new FenwickTree(arr.length + 1);
		ft.fillElements(arr);
		System.out.println(ft.getSum(4));
		System.out.println(ft.getSum(7));
	}
}