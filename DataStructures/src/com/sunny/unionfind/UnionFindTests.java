package com.sunny.unionfind;

import org.testng.annotations.Test;

public class UnionFindTests {
  @Test
  public void testOperationsAtInitilization() {
	  UnionFind unionFind = new UnionFind(3);
	  
	  assert unionFind.getCount() == 3;
	  assert unionFind.find(0) == 0;
	  assert unionFind.find(1) == 1;
	  assert unionFind.find(2) == 2;
	  
	  assert unionFind.connected(0, 1) == false;
  }
  
  @Test
  public void testOperationsAfterSomeOperations() {
	  UnionFind unionFind = new UnionFind(3);
	  
	  unionFind.union(0, 1);
	  assert unionFind.find(0) == 1;
	  assert unionFind.find(1) == 1;
	  assert unionFind.connected(0, 1) == true;
	  assert unionFind.getCount() == 2;
	  
	  unionFind.union(0, 1);         //To check that the operation is idempotent
	  assert unionFind.find(0) == 1;
	  assert unionFind.find(1) == 1;
	  assert unionFind.connected(0, 1) == true;
	  assert unionFind.getCount() == 2;
	  
	  unionFind.union(0, 2);
	  assert unionFind.find(1) == 1;
	  assert unionFind.find(2) == 1;
	  assert unionFind.connected(0, 2) == true;
	  assert unionFind.connected(1, 2) == true;
	  assert unionFind.getCount() == 1;
  }
}
