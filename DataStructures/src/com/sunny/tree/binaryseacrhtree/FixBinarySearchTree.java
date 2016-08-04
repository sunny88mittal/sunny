package com.sunny.tree.binaryseacrhtree;

import com.sunny.tree.binarytree.BinaryTreeUtil;

public class FixBinarySearchTree {

	private static TreeNode firstNode, secondNode, previousNode;

	public static void main(String args[]) {
		String str = "10-5,8-2,20,15,30";
		str = "10-5,15-2,8,20,30";
		TreeNode root = BinaryTreeUtil.getBinaryTree(str);
		fixBinaryTree(root);
		int temp = firstNode.value;
		firstNode.value = secondNode.value;
		secondNode.value = temp;
		BinarySearchTreeUtil.traverseLevelOrder(root);
	}

	private static void fixBinaryTree(TreeNode root) {
		if (root == null)
			return; // Nothing to do in this case

		fixBinaryTree(root.leftChild);
		if (previousNode != null) {
			if (previousNode.value > root.value) {
				if (firstNode == null) {
					firstNode = previousNode;
				}
				secondNode = root;
			}
		}
		previousNode = root;
		fixBinaryTree(root.rightChild);
	}
}
