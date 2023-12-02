package solution1000to1100;

import java.util.Stack;

public class Solution1081 {
	public String smallestSubsequence(String s) {
		int[] count = new int[26];
		for (char ch : s.toCharArray()) {
			++count[ch - 'a'];
		}

		Stack<Character> monoStack = new Stack<>();
		boolean added[] = new boolean[26];
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			--count[ch - 'a'];
			if (!added[ch - 'a']) {
				while (monoStack.size() > 0) {
					if (monoStack.peek() > ch && count[monoStack.peek() - 'a'] > 0) {
						added[monoStack.peek() - 'a'] = false;
						monoStack.pop();
					} else {
						break;
					}
				}
				monoStack.add(ch);
				added[ch - 'a'] = true;
			}
		}

		String res = "";
		while (!monoStack.isEmpty()) {
			res = monoStack.pop() + "" + res;
		}

		return res;
	}

	public static void main(String args[]) {
		Solution1081 obj = new Solution1081();
		obj.smallestSubsequence("bcabc");
	}
}
