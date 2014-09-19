package com.sunny.tree.intervaltree;

public class IntervalTree {

	// Method to add the interval
	public static IntervalTreeNode insertInterval(IntervalTreeNode root,
			Interval interval) {
		if (root == null) {
			return new IntervalTreeNode(interval);
		}

		if (interval.low < root.interval.low) {
			root.left = insertInterval(root.left, interval);
		} else {
			root.right = insertInterval(root.right, interval);
		}

		if (root.max < interval.high) {
			root.max = interval.high;
		}

		return root;
	}

	// Method to search for an interval
	public static Interval searchInterval(IntervalTreeNode root, Interval interval) {
		if (root == null) {
			return null;
		}
		
		if (interval.low <= root.interval.high && root.interval.low <= interval.high){
			return root.interval;
		}
		
		if (root.left != null && root.left.max >= interval.low) {
			return searchInterval(root.left, interval);
		}
		
		return searchInterval(root.right, interval);
	}
	
	//Print the interval tree in inorder
	public static void inOrder(IntervalTreeNode root) {
		if (root == null) {
			return;
		}
		inOrder(root.left);
		System.out.println(root);
		inOrder(root.right);
	}
}
