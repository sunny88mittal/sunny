package com.sunny.tree.binaryseacrhtree;

import java.util.ArrayList;
import java.util.List;

public class RootToLeafPathOneLine {

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
        
        printRootToLeafOneLine(root1, new ArrayList<Integer>());
	}
	
	private static void printRootToLeafOneLine(TreeNode tree, List<Integer> elements){
		if (tree.leftChild == null && tree.rightChild == null) {
			String toPrint = "[";
			for (Integer integer : elements) {
				toPrint += integer + ", ";
			}
			toPrint += tree.value + "]";
			System.out.println(toPrint);
			return;
		}
		
		elements.add(tree.value);
		if (tree.leftChild != null) {
			printRootToLeafOneLine(tree.leftChild, elements);	
		} else {
			System.out.println(elements);	
		}
		
		if (tree.rightChild != null) {
			printRootToLeafOneLine(tree.rightChild, elements);	
		} else {
			System.out.println(elements);	
		}
		
		elements.remove(elements.size() - 1);
	}
}
