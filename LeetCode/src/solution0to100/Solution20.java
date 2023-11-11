package solution0to100;

import java.util.Stack;

public class Solution20 {
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (char ch : s.toCharArray()) {
			if (stack.isEmpty() || ch == '(' || ch == '{' || ch == '[') {
				stack.push(ch);
			} else if (ch == ')' && stack.peek() == '(') {
				stack.pop();
			} else if (ch == '}' && stack.peek() == '{') {
				stack.pop();
			} else if (ch == ']' && stack.peek() == '[') {
				stack.pop();
			} else {
				stack.push(ch);
			}
		}

		return stack.isEmpty();
	}
}
