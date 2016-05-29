package com.sunny.tree.binaryseacrhtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
public class LevelOrderTraversalSpiralForm {
	
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
        
        node = new TreeNode();
        node.value = 5;
        TreeUtil.addChildren(root1, node);
        
        node = new TreeNode();
        node.value = 7;
        TreeUtil.addChildren(root1, node);
        
        node = new TreeNode();
        node.value = 13;
        TreeUtil.addChildren(root1, node);
        
        node = new TreeNode();
        node.value = 15;
        TreeUtil.addChildren(root1, node);
        
        node = new TreeNode();
        node.value = 28;
        TreeUtil.addChildren(root1, node);
        
        node = new TreeNode();
        node.value = 32;
        TreeUtil.addChildren(root1, node);
        
        node = new TreeNode();
        node.value = 38;
        TreeUtil.addChildren(root1, node);
        
        node = new TreeNode();
        node.value = 42;
        TreeUtil.addChildren(root1, node);
        
        traverseLevelOrderSpiralForm(root1);
	}
	
	private static void traverseLevelOrderSpiralForm(TreeNode node){
        List<TreeNode> queue = new ArrayList<TreeNode>(); //Queue to hold elements from left to right
        
        if (node == null) {
        	return;
        }
        
        System.out.println(node.value);
        if (node.leftChild != null) {
        	queue.add(node.leftChild);	
        }
        if (node.rightChild != null) {
        	queue.add(node.rightChild);	
        }
       
        int count = 2;
        
        while (queue.size() !=0) {
        	int size = queue.size(); // No of elements to print at current level
        	String toPrint = "";
        	if (count %2 == 1) {
        		Collections.reverse(queue);
        	}
        	for (int i=0; i<size; i++) {
        		TreeNode treeNode = queue.remove(0);
        		if (count %2 == 1) {
        			int position = size-i-1;
        			if (treeNode.rightChild != null) {
            			queue.add(position,treeNode.rightChild);
            		}
        			
        			if (treeNode.leftChild != null) {
            			queue.add(position,treeNode.leftChild);
            		}	
        		} else {
        			if (treeNode.leftChild != null) {
            			queue.add(treeNode.leftChild);
            		}
            		if (treeNode.rightChild != null) {
            			queue.add(treeNode.rightChild);
            		}
        		}
        		
        		//construct the print string
        		toPrint += treeNode.value;
        		if (i != size-1) {
        			toPrint += " ,"; 	
        		}
        	}
        	System.out.println(toPrint);
        	count++;
        }
    }
}
