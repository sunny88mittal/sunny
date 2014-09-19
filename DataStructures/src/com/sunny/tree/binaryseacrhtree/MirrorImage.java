package com.sunny.tree.binaryseacrhtree;

public class MirrorImage {
	
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
        
        System.out.println("Original Tree");
        TreeUtil.traverseLevelOrder(root1);
        
        mirrorImage(root1);
        
        System.out.println("Mirror image of Tree");
        TreeUtil.traverseLevelOrder(root1);
	}
	
	private static void mirrorImage(TreeNode tree) {
	    if (tree != null) {
	    	TreeNode temp = tree.leftChild;
		    tree.leftChild = tree.rightChild;
		    tree.rightChild = temp;
		    mirrorImage(tree.leftChild);
		    mirrorImage(tree.rightChild);	
	    }
	}
}
