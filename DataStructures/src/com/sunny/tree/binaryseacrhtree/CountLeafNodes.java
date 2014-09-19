package com.sunny.tree.binaryseacrhtree;

public class CountLeafNodes {
	
	public static void main (String args[]) {
		//Tree 1
		TreeNode root1 = new TreeNode();
        root1.value = 25;
        
        TreeNode node = new TreeNode();
        node.value = 12;
        TreeUtil.addChildren(root1, node);
        
        node = new TreeNode();
        node.value = 35;
        TreeUtil.addChildren(root1, node);
        
        node = new TreeNode();
        node.value = 6;
        TreeUtil.addChildren(root1, node);
        
        node = new TreeNode();
        node.value = 14;
        TreeUtil.addChildren(root1, node);
        
        node = new TreeNode();
        node.value = 30;
        TreeUtil.addChildren(root1, node);
        
        node = new TreeNode();
        node.value = 40;
        TreeUtil.addChildren(root1, node);
        
        System.out.println("Leaf nodes are:" + countLeafNodes(root1));
	}
	
	private static int countLeafNodes (TreeNode tree) {
		if (tree == null) {
			return 0;
		} else if (tree.leftChild ==  null && tree.rightChild == null) {
			return 1;
		}
		
		return countLeafNodes(tree.leftChild) + countLeafNodes(tree.rightChild);
	}
}
