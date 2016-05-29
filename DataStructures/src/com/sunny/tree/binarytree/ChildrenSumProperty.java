package com.sunny.tree.binarytree;

import com.sunny.tree.binaryseacrhtree.TreeNode;

/**
 * http://www.geeksforgeeks.org/check-for-children-sum-property-in-a-binary-tree/
 * @author sumittal
 * 
 *                10   
 *              /   \ 
 *             8     2
 *           /  \
 *          3    5
 *
 */
public class ChildrenSumProperty {

	public static void main (String args[]) {
		//Tree
		
		//Root
		TreeNode root = new TreeNode();
        root.value = 10;
        
        //Level 1
        TreeNode level1node1 = new TreeNode();
        level1node1.value = 8;
        root.leftChild = level1node1;
        
        TreeNode level1node2 = new TreeNode();
        level1node2.value = 2;
        root.rightChild = level1node2;
        
        //Level2
        TreeNode level2node1 = new TreeNode();
        level2node1.value = 3;
        root.leftChild = level1node1;
        
        TreeNode level2node2 = new TreeNode();
        level2node2.value = 5;
        root.rightChild = level1node2;
        
        level1node1.leftChild = level2node1;
        level1node1.rightChild = level2node2;
        
        System.out.println("Sum property holds:"+ validateSumProperty(root));
	}
	
	private static boolean validateSumProperty(TreeNode tree) {
		if (tree.leftChild == null && tree.rightChild == null) {
			return true;
		}
		
		return (tree.value == tree.leftChild.value + tree.rightChild.value) &&
			   validateSumProperty(tree.leftChild) &&
			   validateSumProperty(tree.rightChild);
	}
}
