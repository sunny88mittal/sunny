package com.sunny.string.trie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RWayTrie<T> {

	private static final int R = 256;

	private Node root;

	public RWayTrie() {
		root = new Node();
	}

	/**
	 * Class whose objects makes up the tree
	 */
	private class Node {
		T value;
		List<Node> nodes;

		public Node() {
			nodes = new ArrayList<Node>(R);
			for (int i = 0; i < R; i++) {
				nodes.add(null);
			}
		}
	}

	/**
	 * Adds the key value pair to the tree
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, T value) {
		Node rootNode = root;
		char c;
		int count = 0;
		while (count < key.length()) {
			c = key.charAt(count);
			Node charNode = rootNode.nodes.get(c);
			if (charNode == null) {
				charNode = new Node();
				rootNode.nodes.set(c, charNode);
			}
			rootNode = charNode;
			count++;
		}
		rootNode.value = value;
	}

	/**
	 * Gets the value for the key
	 * 
	 * @param key
	 * @return
	 */
	public T getValue(String key) {
		Node node = getNode(key);
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

	/**
	 * Delete the key
	 * @param key
	 */
	public boolean delete(String key) {
		List<Node> parentsList = new LinkedList<Node>();
		Node rootNode = root;
		int count = 0;
		char c = key.charAt(count);
		Node charNode = null;
		//Check if the key exists
		while (count < key.length()) {
			c = key.charAt(count);
			charNode = rootNode.nodes.get(c);
			if (charNode == null) {
				return false;
			}
			parentsList.add(0, rootNode);
			rootNode = charNode;
			count++;
		}
		
		//Clear the value
		if (rootNode.value == null) {
			return false;
		}
		rootNode.value = null;
		
		//Delete the node and its parents till the level nobody has any value
		for (int i=0; i<parentsList.size(); i++) {
			Node parentNode = parentsList.get(i);
			if (isNodeDeletable(charNode)) {
				parentNode.nodes.set(key.charAt(key.length() - 1- i), null);
			}
			charNode = parentNode;
		}
		
		return true;
	}
	
	private boolean isNodeDeletable(Node node) {
		if (node.value != null) {
			return false;
		}
		for (Node inode : node.nodes) {
			if (inode != null) {
				return false;
			}
		}
		return true;
	}

	private Node getNode(String key) {
		Node rootNode = root;
		char c;
		int count = 0;
		while (count < key.length()) {
			c = key.charAt(count);
			Node charNode = rootNode.nodes.get(c);
			if (charNode == null) {
				return null;
			}
			rootNode = charNode;
			count++;
		}
		return rootNode;
	}
}
