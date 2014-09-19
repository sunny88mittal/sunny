package com.sunny.unionfind;

public class UnionFind {
	
	/**Array to store the leaders for the nodes **/
	private int[] leaders; 
	
	/**Array to store the rank for the nodes**/
	private byte[] rank;
	
	/**Total no. of nodes**/
	int count;

	public UnionFind(int count) {
		this.count = count;
		leaders = new int[count];
		rank = new byte[count];
		for (int i=0; i<count; i++) {
			leaders[i] = i;
			rank[i] = 0;
		}
	}
	
	public int getCount() {
		return count;
	}
	
	public int find(int p) {
		while (leaders[p] != p) {
			leaders[p] = leaders[leaders[p]]; //Path Compression by halving
			p = leaders[p];
		}
		return p;
	}
	
	public boolean connected (int p, int q) {
		return find(p) == find(q);
	}
	
	public void union(int p, int q) {
		int leader1 = find(p);
		int leader2 = find(q);
		
		if (leader1 == leader2) {  //Both belong to the same union already
			return;
		}
		
		if (rank[leader1] < rank[leader2]) {
			leaders[leader1] = leader2;
		} else if(rank[leader1] > rank[leader2]) {
			leaders[leader2] = leader1;
		} else {
			leaders[leader1] = leader2;
			rank[leader2]++;
		}
		count--;
	}
}
