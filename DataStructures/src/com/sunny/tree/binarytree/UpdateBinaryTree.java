package com.sunny.tree.binarytree;

import com.sunny.tree.binaryseacrhtree.BinarySearchTreeUtil;
import com.sunny.tree.binaryseacrhtree.TreeNode;

//Update binary tree such that left = parent /2. right by parent % 2.
public class UpdateBinaryTree {

	public static void main(String[] args) {
		String str = "1-2,3-4,5,6,7";
		TreeNode binaryTree = BinaryTreeUtil.getBinaryTree(str);
		updateTree(binaryTree);
		BinarySearchTreeUtil.traverseLevelOrder(binaryTree);
	}

	private static void updateTree(TreeNode root) {
		if (root != null) {
			updateTree(root.leftChild);
			updateTree(root.rightChild);
			if (root.leftChild != null) {
				root.leftChild.value = root.value / 2;
			}
			if (root.rightChild != null) {
				root.rightChild.value = root.value % 2;
			}
		} else {
			return;
		}
	}
}