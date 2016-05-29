package com.sunny.tree.intervaltree;

public class IntervalTreeNode {

	Interval interval;
	
	int max;
	
	IntervalTreeNode left;
	
	IntervalTreeNode right;
	
	IntervalTreeNode (Interval interval) {
		this.interval = interval;
		this.max = interval.high;
	}
	
	public String toString() {
		return "[" + interval.low + " " + interval.high + "]" + " max = " + max; 
	}
}
