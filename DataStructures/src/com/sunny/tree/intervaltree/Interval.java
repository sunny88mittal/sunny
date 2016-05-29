package com.sunny.tree.intervaltree;

class Interval {
	int low;
	
	int high;
	
	public Interval(int low, int high) {
	     this.low = low;
	     this.high = high;
	}
	
	public String toString() {
		return "[" + low + " " + high + "]"; 
	}
}
