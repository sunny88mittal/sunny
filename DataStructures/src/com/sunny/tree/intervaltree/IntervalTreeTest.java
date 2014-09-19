package com.sunny.tree.intervaltree;

import org.testng.annotations.Test;

public class IntervalTreeTest {
	@Test
	public void f() {
	   Interval[] intervals = new Interval [] {
			   new Interval (15,20),
			   new Interval (10,30),
			   new Interval (17,19),
			   new Interval (5,20),
			   new Interval (12,15),
			   new Interval (30,40),
	   };
	   
	   IntervalTreeNode root = null;
	   for (int i=0; i<intervals.length; i++) {
		   root = IntervalTree.insertInterval(root, intervals[i]);
	   }
	   
	   IntervalTree.inOrder(root);
	   
	   System.out.println(IntervalTree.searchInterval(root, new Interval(6, 7)));
	}
}
