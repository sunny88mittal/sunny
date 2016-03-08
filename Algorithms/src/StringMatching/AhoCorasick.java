package StringMatching;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AhoCorasick {

	public static void main(String args[]) {
		String[] dict = new String[] { "e", "h", "she", "he" };
		RWayTrie<String> trie = new RWayTrie<String>();
		for (String str : dict) {
			trie.put(str, str);
		}
		trie.createDictSuffixLinks();
		trie.printSuffixes("she");
	}

	private static class RWayTrie<T> {

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
			List<T> suffixValues = new ArrayList<T>();
			Node suffixLink;

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
		 * 
		 * @param key
		 */
		public boolean delete(String key) {
			List<Node> parentsList = new LinkedList<Node>();
			Node rootNode = root;
			int count = 0;
			char c = key.charAt(count);
			Node charNode = null;
			// Check if the key exists
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

			// Clear the value
			if (rootNode.value == null) {
				return false;
			}
			rootNode.value = null;

			// Delete the node and its parents till the level nobody has any
			// value
			for (int i = 0; i < parentsList.size(); i++) {
				Node parentNode = parentsList.get(i);
				if (isNodeDeletable(charNode)) {
					parentNode.nodes.set(key.charAt(key.length() - 1 - i), null);
				}
				charNode = parentNode;
			}

			return true;
		}

		/**
		 * Creates the suffix links for the nodes in the trie
		 */
		public void createDictSuffixLinks() {
			// Mark the first level nodes suffix link as root
			List<Node> queue = new ArrayList<Node>();
			for (Node node : root.nodes) {
				if (node != null) {
					node.suffixLink = root;
					queue.add(node);
				}
			}

			// Recursively find the suffix link for all nodes using breadth
			// first search exploration
			while (!queue.isEmpty()) {
				Node node = queue.remove(0);
				for (int i = 0; i < R; i++) {
					Node iNode = node.nodes.get(i);
					if (iNode != null) {
						queue.add(iNode);
						Node suffixLink = findSuffixLink(node.suffixLink, i);
						iNode.suffixLink = suffixLink;
						iNode.suffixValues.addAll(suffixLink.suffixValues);
						if (suffixLink.value != null){
							iNode.suffixValues.add(suffixLink.value);
						}
					}
				}
			}
		}

		public void printSuffixes(String text) {
			Node node = root;
			for (int i=0; i<text.length(); i++) {
				char ch = text.charAt(i);
				if (node.nodes.get(ch) != null) {
                	node = node.nodes.get(ch);
                } else if (node.suffixLink != null) {
                	node = node.suffixLink;
                	i--;
                	continue;
                } else {
                	continue;
                }
                
                if (node.value != null) {
                	System.out.println(node.value);
                }
                
                for (T t: node.suffixValues) {
                	System.out.println(t);
                }
			}
		}

		private Node findSuffixLink(Node node, int i) {
			if (node.nodes.get(i) != null) { // Found the suffix link node
				return node.nodes.get(i);
			}
			
			if (node.suffixLink == null) { // We have reached root
				return node;
			}

			return findSuffixLink(node.suffixLink, i); // Go one level up
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
}