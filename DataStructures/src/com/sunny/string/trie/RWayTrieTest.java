package com.sunny.string.trie;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RWayTrieTest {

	private RWayTrie<Integer> rwayTrie;

	@BeforeMethod
	public void createTrie() {
		rwayTrie = new RWayTrie<Integer>();
		rwayTrie.put("sea", 2);
		rwayTrie.put("sells", 4);
		rwayTrie.put("she", 1);
		rwayTrie.put("shells", 1);
		rwayTrie.put("by", 1);
		rwayTrie.put("the", 2);
		rwayTrie.put("shore", 3);
	}

	@Test
	public void testContains() {
		assert rwayTrie.contains("sea") == true;
		assert rwayTrie.contains("sells") == true;
		assert rwayTrie.contains("she") == true;
		assert rwayTrie.contains("shells") == true;
		assert rwayTrie.contains("by") == true;
		assert rwayTrie.contains("the") == true;
		assert rwayTrie.contains("shore") == true;

		assert rwayTrie.contains("s") == false;
		assert rwayTrie.contains("seaa") == false;
		assert rwayTrie.contains("sellsa") == false;
		assert rwayTrie.contains("sellh") == false;
	}

    @Test
	public void testGetValue() {
		assert rwayTrie.getValue("sea") == 2;
		assert rwayTrie.getValue("sells") == 4;
		assert rwayTrie.getValue("she") == 1;
		assert rwayTrie.getValue("shells") == 1;
		assert rwayTrie.getValue("by") == 1;
		assert rwayTrie.getValue("the") == 2;
		assert rwayTrie.getValue("shore") == 3;

		assert rwayTrie.getValue("s") == null;
		assert rwayTrie.getValue("seaa") == null;
		assert rwayTrie.getValue("sellsa") == null;
		assert rwayTrie.getValue("sellh") == null;
	}
	
	@Test
	public void testDelete() {
		assert rwayTrie.delete("sea") == true;
		assert rwayTrie.delete("sells") == true;
		assert rwayTrie.delete("she") == true;
		assert rwayTrie.delete("s") == false;
		assert rwayTrie.delete("seaa") == false;
		assert rwayTrie.delete("sellsa") == false;
		assert rwayTrie.delete("sellh") == false;
		assert rwayTrie.delete("shells") == true;
		assert rwayTrie.delete("by") == true;
		assert rwayTrie.delete("the") == true;
		assert rwayTrie.delete("shore") == true;
		assert rwayTrie.delete("sea") == false;
		assert rwayTrie.delete("sells") == false;
		
		assert rwayTrie.contains("sea") == false;
		assert rwayTrie.contains("sells") == false;
		assert rwayTrie.contains("she") == false;
		assert rwayTrie.contains("shells") == false;
		assert rwayTrie.contains("by") == false;
		assert rwayTrie.contains("the") == false;
		assert rwayTrie.contains("shore") == false;
	}
	
}
