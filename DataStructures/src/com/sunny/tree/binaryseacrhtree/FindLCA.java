/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sunny.tree.binaryseacrhtree;

/**
 *              25
 *          /        \
 *        12           35
 *       /  \         /    \
 *      6   14       30     40
 *     / \   / \     / \    / \
 *    5   7 13  15  28  32 38  42
 * @author sumittal
 *
 */

public class FindLCA {

	public static void main(String args[]) {
		int[] values = new int[] { 25, 12, 35, 6, 14, 30, 40, 5, 7, 13, 15, 28, 32, 38, 42 };
		TreeNode root1 = BinarySearchTreeUtil.getBinarySearchTree(values);
		System.out.println(getLCA(root1, 30, 38));
	}

	private static int getLCA(TreeNode root, int a, int b) {
		TreeNode lca = root;
		if (a > b) {
			int temp = a;
			a = b;
			b = temp;
		}
		while (true) {
			if ((lca.value == a || lca.value == b) || (lca.value > a && lca.value < b)) {
				break;
			}

			if (lca.value > a && lca.value > b) {
				lca = lca.leftChild;
			} else {
				lca = lca.rightChild;
			}
		}
		return lca.value;
	}
}
