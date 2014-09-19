package com.sunny.tree.trie;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TernaryTrieTest {

	private TernaryTries<Integer> ternaryTrie;

	@BeforeMethod
	public void createTrie() {
		ternaryTrie = new TernaryTries<Integer>();
		ternaryTrie.put("sea", 2);
		ternaryTrie.put("sells", 4);
		ternaryTrie.put("she", 1);
		ternaryTrie.put("shells", 1);
		ternaryTrie.put("by", 1);
		ternaryTrie.put("the", 2);
		ternaryTrie.put("shore", 3);
	}

	@Test
	public void testContains() {
		assert ternaryTrie.contains("sea") == true;
		assert ternaryTrie.contains("sells") == true;
		assert ternaryTrie.contains("she") == true;
		assert ternaryTrie.contains("shells") == true;
		assert ternaryTrie.contains("by") == true;
		assert ternaryTrie.contains("the") == true;
		assert ternaryTrie.contains("shore") == true;
		
		assert ternaryTrie.contains("s") == false;
		assert ternaryTrie.contains("seaa") == false;
		assert ternaryTrie.contains("sellsa") == false;
		assert ternaryTrie.contains("sellh") == false;
	}

    @Test
	public void testGetValue() {
		assert ternaryTrie.getValue("sea") == 2;
		assert ternaryTrie.getValue("sells") == 4;
		assert ternaryTrie.getValue("she") == 1;
		assert ternaryTrie.getValue("shells") == 1;
		assert ternaryTrie.getValue("by") == 1;
		assert ternaryTrie.getValue("the") == 2;
		assert ternaryTrie.getValue("shore") == 3;

		assert ternaryTrie.getValue("s") == null;
		assert ternaryTrie.getValue("seaa") == null;
		assert ternaryTrie.getValue("sellsa") == null;
		assert ternaryTrie.getValue("sellh") == null;
	}
	
    @Test
	public void testDelete() {
		ternaryTrie.delete("sea");
		ternaryTrie.delete("sells");
		ternaryTrie.delete("she");
		ternaryTrie.delete("s"); 
		ternaryTrie.delete("seaa"); 
		ternaryTrie.delete("sellsa"); 
		ternaryTrie.delete("sellh");
		ternaryTrie.delete("shells");
		ternaryTrie.delete("by");
		ternaryTrie.delete("the");
		ternaryTrie.delete("shore");
		ternaryTrie.delete("sea");
		ternaryTrie.delete("sells"); 
		
		assert ternaryTrie.contains("sea") == false;
		assert ternaryTrie.contains("sells") == false;
		assert ternaryTrie.contains("she") == false;
		assert ternaryTrie.contains("shells") == false;
		assert ternaryTrie.contains("by") == false;
		assert ternaryTrie.contains("the") == false;
		assert ternaryTrie.contains("shore") == false;
	}
	
}
