/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sunny.tree.binaryseacrhtree;

/**
 *
 * @author sumittal
 * Tests all the operations for a tree of the form
 *            25
 *          /    \
 *        12      35
 *       /  \    /  \
 *      6   14  30   40
 *  
 *  Pre Order  : 25,12,6,14,35,30,40
 *  In  Order  : 6,12,14,25,30,35,40
 *  Post Order : 6,14,12,30,40,35,25
 *  Level Order: 25,12,35,6,14,30,40
 * 
 */
public class TestBinarySearchTree {
    
    public static void main(String args[]){
        TreeNode root = new TreeNode();
        root.value = 25;
        
        TreeNode node = new TreeNode();
        node.value = 12;
        BinarySearchTreeUtil.addChildren(root, node);
        
        node = new TreeNode();
        node.value = 35;
        BinarySearchTreeUtil.addChildren(root, node);
        
        node = new TreeNode();
        node.value = 6;
        BinarySearchTreeUtil.addChildren(root, node);
        
        node = new TreeNode();
        node.value = 14;
        BinarySearchTreeUtil.addChildren(root, node);
        
        node = new TreeNode();
        node.value = 30;
        BinarySearchTreeUtil.addChildren(root, node);
        
        node = new TreeNode();
        node.value = 40;
        BinarySearchTreeUtil.addChildren(root, node);
        
        BinarySearchTreeUtil.traversePreOrder(root);
        BinarySearchTreeUtil.traverseInOrder(root);
        BinarySearchTreeUtil.traversePostOrder(root);
        BinarySearchTreeUtil.traverseLevelOrder(root);
    }
}
