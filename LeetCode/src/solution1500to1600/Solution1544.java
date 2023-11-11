package solution1500to1600;

import java.util.Stack;

public class Solution1544 {
	public String makeGood(String s) {
		Stack<Character> stack = new Stack<>();
		
		for (char ch : s.toCharArray()) {
			if (stack.isEmpty() || Math.abs(stack.peek() - ch) != 32) {
				stack.add(ch);
			}
			
			if (Math.abs(stack.peek() - ch) == 32) {
				stack.pop();
			}
		}
		
		String result = "";
		while (!stack.isEmpty()) {
			result = stack.pop() + result;
		}
		
		return result;
	}
}
