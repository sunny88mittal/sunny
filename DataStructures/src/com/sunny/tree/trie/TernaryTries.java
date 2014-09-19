package com.sunny.tree.trie;

import java.util.ArrayList;
import java.util.List;

public class TernaryTries<T> {

	private Node root;

	/**
	 * Class to store the nodes of the trie
	 */
	private class Node {
		
		public Node(char key) {
			this.key = key;
		}
		
		char key;

		T value;

		Node leftNode;

		Node middleNode;

		Node rightNode;
		
		public String toString() {
			return key + "";
		}
	}

	/**
	 * Adds the key value pair to the tree
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, T value) {
		root = put (root, key, value, 0);
	}
	
	private Node put (Node root, String key, T value, int index) {
		char c = key.charAt(index);
		if (root == null) {
			root = new Node(c);
		}
		
		if (c < root.key) {
			root.leftNode = put (root.leftNode, key, value, index);
		} else if (c > root.key) {
			root.rightNode = put (root.rightNode, key, value, index);
		} else if (index < key.length() - 1) {
			root.middleNode = put (root.middleNode, key, value, index+1);
		} else {
			root.value = value;
		}
		return root;
	}

	/**
	 * Gets the value for the key
	 * 
	 * @param key
	 * @return
	 */
	public T getValue(String key) {
		Node node = getNode(root, key, 0);
		return node == null ? null : node.value;
	}

	/**
	 * Checks whether the key exists in the tree
	 * 
	 * @param key
	 * @return
	 */
	public boolean contains(String key) {
		return getValue(key) != null;
	}

	private Node getNode(Node root, String key, int index) {
		if (root == null) {
			return null;
		}
		
		char c = key.charAt(index);
		if (c < root.key) {
			return getNode(root.leftNode, key, index);
		} else if (c > root.key) {
			return getNode(root.rightNode, key, index);
		} else if (index < key.length() - 1) {
			return getNode(root.middleNode, key, index + 1);
		} else {
			return root;
		}
	}

	/**
	 * Delete the key
	 * 
	 * @param key
	 */
	public void delete(String key) {
		deleteNode(root, key, 0);
	}
	
	private boolean deleteNode(Node root, String key, int index) {
		if (root == null) {
			return false;
		}
		
		char c = key.charAt(index);
		if (c < root.key) {
			boolean deleteNode =  deleteNode(root.leftNode, key, index);
			if (deleteNode) {
				root.leftNode = null;
			}
		} else if (c > root.key) {
			boolean deleteNode = deleteNode(root.rightNode, key, index);
			if (deleteNode) {
				root.rightNode = null;
			}
		} else if (index < key.length() - 1) {
			boolean deleteNode = deleteNode(root.middleNode, key, index + 1);
			if (deleteNode) {
				root.middleNode = null;
			}
		} else {
			root.value = null;
		}
		return isNodeDeletable(root);
	}
	
	private boolean isNodeDeletable(Node node) {
		return node.value == null &&
			   node.leftNode == null &&
		       node.rightNode == null &&
		       node.middleNode == null;
	}
	
	public void printInOrderTree() {
		List <Node> list = new ArrayList<Node>();
		list.add(root);
		System.out.println();
		while (list.size() != 0) {
			System.out.println(list);
			List<Node> tempList = new ArrayList<Node>();
			for (Node node : list) {
			   tempList.add(node);	
			}
			list.clear();
			for (Node node : tempList) {
				if (node.leftNode != null) {
					list.add(node.leftNode);
				}
				if (node.middleNode != null) {
					list.add(node.middleNode);
				}
				if (node.rightNode != null) {
					list.add(node.rightNode);
				}
			}
		}
	}
}
