package com.sunny.tree.binaryseacrhtree;

public class IdenticalTrees {
	
	public static void main (String args[]) {
		//Tree 1
		TreeNode root1 = new TreeNode();
        root1.value = 25;
        
        TreeNode node = new TreeNode();
        node.value = 12;
        BinarySearchTreeUtil.addChildren(root1, node);
        
        node = new TreeNode();
        node.value = 35;
        BinarySearchTreeUtil.addChildren(root1, node);
        
        node = new TreeNode();
        node.value = 6;
        BinarySearchTreeUtil.addChildren(root1, node);
        
        node = new TreeNode();
        node.value = 14;
        BinarySearchTreeUtil.addChildren(root1, node);
        
        node = new TreeNode();
        node.value = 30;
        BinarySearchTreeUtil.addChildren(root1, node);
        
        node = new TreeNode();
        node.value = 40;
        BinarySearchTreeUtil.addChildren(root1, node);
        
        //Tree2
        TreeNode root2 = new TreeNode();
        root2.value = 25;
        
        node = new TreeNode();
        node.value = 12;
        BinarySearchTreeUtil.addChildren(root2, node);
        
        node = new TreeNode();
        node.value = 35;
        BinarySearchTreeUtil.addChildren(root2, node);
        
        node = new TreeNode();
        node.value = 6;
        BinarySearchTreeUtil.addChildren(root2, node);
        
        node = new TreeNode();
        node.value = 14;
        BinarySearchTreeUtil.addChildren(root2, node);
        
        node = new TreeNode();
        node.value = 30;
        BinarySearchTreeUtil.addChildren(root2, node);
        
        node = new TreeNode();
        node.value = 40;
        BinarySearchTreeUtil.addChildren(root2, node);
        
        System.out.println("Trees are same:" + compareTrees(root1, root2));
	}

	private static boolean compareTrees (TreeNode tree1, TreeNode tree2) {
		if (tree1 == null && tree2 == null) {
			return true;
		} else if (tree1 != null && tree2 != null) {
			return (tree1.value == tree2.value) &&
				   compareTrees(tree1.leftChild, tree2.leftChild) &&
				   compareTrees(tree1.rightChild, tree2.rightChild);
		}
		
		return false;
	}
	
}
