/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sunny.tree.binaryseacrhtree;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sumittal
 */
public class BinarySearchTreeUtil {
    
	
	/**
	 * Creates a binary search tree from the values assuming first node as root
	 */
	public static TreeNode getBinarySearchTree(int[] values){
		TreeNode root = new TreeNode();
		root.value = values[0];
		for (int i = 1; i < values.length; i++) {
			TreeNode node = new TreeNode();
			node.value = values[i];
			addChildren(root, node);
		}
		return root;
    }
	
    public static void addChildren(TreeNode root, TreeNode node){
        if (node.value < root.value){
            if( root.leftChild == null){
                root.leftChild = node;
            }else{
                addChildren(root.leftChild, node);
            }
        }else{
            if( root.rightChild == null){
                root.rightChild = node;
            }else{
                addChildren(root.rightChild, node);
            }
        }   
    }
    
    /**
     * Root-->Left-->Right
     * @param node 
     */
    public static void traversePreOrder(TreeNode node){
        if(node != null){
           System.out.println(node.value);
           traversePreOrder(node.leftChild);
           traversePreOrder(node.rightChild);
        } 
    }
    
    /**
     * Left-->Root-->right
     * @param node 
     */
    public static void traverseInOrder(TreeNode node){
        if(node != null){
           traverseInOrder(node.leftChild);
           System.out.println(node.value);
           traverseInOrder(node.rightChild);
        }
    }
    
    /**
     * Left-->Right-->Root
     * @param node 
     */
    public static void traversePostOrder(TreeNode node){
        if(node != null){
           traversePostOrder(node.leftChild);
           traversePostOrder(node.rightChild);
           System.out.println(node.value);
        }
    }
    
    public static void traverseLevelOrder(TreeNode node){
        List<TreeNode> list = new LinkedList<TreeNode>();
        System.out.println(node.value);
        if(node.leftChild != null){
            list.add(node.leftChild);
        }
        if(node.rightChild != null){
            list.add(node.rightChild);
        }
        
        while(!list.isEmpty()) {
            TreeNode removed = list.remove(0);
            System.out.println(removed.value);
            if(removed.leftChild != null){
                list.add(removed.leftChild);
            }
            if(removed.rightChild != null){
               list.add(removed.rightChild);
            }
        }
    }
}
