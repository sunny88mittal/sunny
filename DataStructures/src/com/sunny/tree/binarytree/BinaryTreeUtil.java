package com.sunny.tree.binarytree;

import java.util.ArrayList;
import java.util.List;

import com.sunny.tree.binaryseacrhtree.BinarySearchTreeUtil;
import com.sunny.tree.binaryseacrhtree.TreeNode;

public class BinaryTreeUtil {

	/**
	 * Get binary tree by giving the input as in level order as works only for
	 * Separate nodes at a level by -
	 * Separate nodes at same level by ,
	 * complete trees 1-2,3-4,5,6,7-8,9,10,11,12,13,14,15 
	 */
	public static TreeNode getBinaryTree(String str) {
		List<List<Integer>> treeInLevelOrder = new ArrayList<List<Integer>>();
		String tokens[] = str.split("-");
		for (String str1 : tokens) {
			String ttokens[] = str1.split(",");
			List<Integer> values = new ArrayList<Integer>();
			for (String str2 : ttokens) {
				values.add(Integer.parseInt(str2));
			}
			treeInLevelOrder.add(values);
		}
		return getBinaryTree(treeInLevelOrder, 0, 0);
	}

	public static TreeNode getBinaryTree(List<List<Integer>> treeInLevelOrder, int level, int power) {
		if (level >= treeInLevelOrder.size()) {
			return null;
		}

		TreeNode root = new TreeNode();
		root.value = treeInLevelOrder.get(level).get(power);
		root.leftChild = getBinaryTree(treeInLevelOrder, level + 1, 2 * power);
		root.rightChild = getBinaryTree(treeInLevelOrder, level + 1, 2 * power + 1);

		return root;
	}

	public static void main(String args[]) {
		TreeNode tree  =getBinaryTree("1-2,3-4,5,6,7-8,9,10,11,12,13,14,15");
	}
}