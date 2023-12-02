package solution400to500;
import java.util.Stack;

public class Solution402 {

	public String removeKdigits(String num, int k) {
		char[] nums = num.toCharArray();
		Stack<Character> stack = new Stack<>();
		for (char c : nums) {
			while (!stack.isEmpty()) {
				if (stack.peek() > c && k > 0) {
					stack.pop();
					--k;
				} else {
					break;
				}
			}

			stack.push(c);
		}

		while (k > 0) {
			stack.pop();
			--k;
		}

		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		sb.reverse();

		while (sb.length() > 0 && sb.charAt(0) == '0') {
			sb.delete(0, 1);
		}

		return sb.length() > 0 ? sb.toString() : "0";
	}
}
