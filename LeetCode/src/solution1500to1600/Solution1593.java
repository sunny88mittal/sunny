package solution1500to1600;

import java.util.HashSet;
import java.util.Set;

public class Solution1593 {
	public int maxUniqueSplit(String s) {
		int count = 0;
		Set<String> us = new HashSet<String>();
		String temp = "";
		for (char ch : s.toCharArray()) {
			if (!temp.isEmpty() && !us.contains(temp)) {
				++count;
				us.add(temp);
				temp = "";
			}
			temp += ch;
		}
		
		if (!us.contains(temp)) {
			++count;
		}
		
		return count;
	}
	
	public static void main (String args[]) {
		Solution1593 obj = new Solution1593();
		obj.maxUniqueSplit("wwwzfvedwfvhsww");
	}
}
