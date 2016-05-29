/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sunny.tree.binaryseacrhtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * given a tree find its border
 * for e.g. if the tree is
 *   
 *                        10
 *                     
 *               50                   150
 *            
 *          25         75        200          20
 *       
 *      15      35            120         155       250
 * 
 * 
 *  Output should be 50,25,15,35,120,155,250,20,150,10
 * 
 * 
 *   
 *                        10
 *                     
 *               50                   150
 *            
 *          25         75        200        
 *       
 *      15      35   65    30            
 *              
 * Output should be 50,25,15,35,65,30,200,150,10
 */

public class FindBorder {
    
    public static void main (String args[]){
        //Tree 1
        TreeNode root = new TreeNode();
        root.value = 10;
        
        TreeNode node1 = new TreeNode();
        node1.value = 50;
        TreeNode node2 = new TreeNode();
        node2.value = 150;
        
        TreeNode node3 = new TreeNode();
        node3.value = 25;
        TreeNode node4 = new TreeNode();
        node4.value = 75;
        TreeNode node5 = new TreeNode();
        node5.value = 200;
        TreeNode node6 = new TreeNode();
        node6.value = 20;
        
        TreeNode node7 = new TreeNode();
        node7.value = 15;
        TreeNode node8 = new TreeNode();
        node8.value = 35;
        TreeNode node9 = new TreeNode();
        node9.value = 120;
        TreeNode node10 = new TreeNode();
        node10.value = 155;
        TreeNode node11 = new TreeNode();
        node11.value = 250;
        
        root.leftChild = node1;
        root.rightChild = node2;
        
        node1.leftChild = node3;
        node1.rightChild = node4;
        node2.leftChild = node5;
        node2.rightChild = node6;
        
        node3.leftChild = node7;
        node3.rightChild = node8;
        
        node5.leftChild = node9;
        
        node6.leftChild = node10;
        node6.rightChild = node11;
    }
    
    public static List<Integer> getBorder(TreeNode root){
        List<Integer> border = new ArrayList<Integer>();
        
        //Step 1 get all the left border nodes
        //Keep on taking the left childif somewhere it is null continue with right  
        List<TreeNode> leftNodes = new ArrayList<TreeNode>();
        TreeNode temp = root;
        
        border.add(root.value);
        if (root.leftChild != null){
           temp = root.leftChild;
        }else if (root.rightChild != null){
           temp = root.rightChild;
        }
        
        while(temp != null){  
            leftNodes.add(temp);
            border.add(temp.value);
            if (temp.leftChild != null){
                temp = temp.leftChild;
            }else if (temp.rightChild != null){
                temp = temp.rightChild;
            }else {
                temp = null;
            }
        }
        
        //Step2 Get the border nodes from the left part of the tree
        //Take the children of the nodes which are at the level of the last left/ right node
        //Then take the leaf nodes for all those nodes
        Collections.reverse(leftNodes);
        leftNodes.remove(0);
        int depth = 0;
        for (TreeNode tempNode : leftNodes) {
            depth ++;
            int tempi = 0;
            while (tempi < depth){
                
            }
        }
        return border;
    } 
}
