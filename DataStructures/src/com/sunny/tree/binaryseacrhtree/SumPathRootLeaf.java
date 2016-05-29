/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sunny.tree.binaryseacrhtree;

import java.util.ArrayList;
import java.util.List;

/**
   Given a binary tree and a number, return true if the tree has a 
   root-to-leaf path such that adding up all the values along the path equals the given number. 
   Return false if no such path can be found.

   For example, in the above tree root to leaf paths exist with following sums.
                  
               10
              /  \
             /    \ 
            8      2  
           / \     /
          /   \   / 
         3     5  2
              
    21 –> 10 – 8 – 3
    23 –> 10 – 8 – 5
    14 –> 10 – 2 – 2
    
   So the returned value should be true only for numbers 21, 23 and 14. For any other number, returned value should be false.
 */

public class SumPathRootLeaf {
    
    public static void main(String args[]){
        TreeNode root = new TreeNode();
        root.value = 10;
        
        TreeNode node1 = new TreeNode();
        node1.value = 8;
        TreeNode node2 = new TreeNode();
        node2.value = 2;
        
        TreeNode node3 = new TreeNode();
        node3.value = 3;
        TreeNode node4 = new TreeNode();
        node4.value = 5;
        
        TreeNode node5 = new TreeNode();
        node5.value = 2;
        
        root.leftChild = node1;
        root.rightChild = node2;
        
        node1.leftChild = node3;
        node1.rightChild = node4;
        
        node2.leftChild = node5;
        
        //Postive Cases
        executeCase(root, 10);
        executeCase(root, 18);
        executeCase(root, 21);
        executeCase(root, 23);
        executeCase(root, 14);
        
        //Negative Cases
        executeCase(root, 20);
        executeCase(root, 500);
        executeCase(root, -10);
        executeCase(root, 2);
    }
    
    public static void executeCase(TreeNode root, int sum){
        List<Integer> path = returnPath(root, sum);
        if ( path.size() > 0){
           System.out.println(path);    
        }else{
            System.out.println("No Path possible");
        }
    }
    
    public static List<Integer> returnPath(TreeNode root, int sum){
         List<Integer> pathList = new ArrayList<Integer>();
         if (root != null) {
             int value = root.value;
             TreeNode leftchild = root.leftChild;
             TreeNode rightChild = root.rightChild;
             if (value < sum ) {
                 pathList.add(value);
                 List<Integer> leftChildResult = returnPath(leftchild, sum - value);
                 List<Integer> rightChildResult = returnPath(rightChild, sum - value);
                 if (!leftChildResult.isEmpty()) {
                     pathList.addAll(leftChildResult);
                 }else if (!rightChildResult.isEmpty()) {
                     pathList.addAll(rightChildResult);
                 }else{
                     pathList.clear();
                 }
             }else if (value == sum){
                 pathList.add(value);
             }
         }
         return pathList;
    }
}
