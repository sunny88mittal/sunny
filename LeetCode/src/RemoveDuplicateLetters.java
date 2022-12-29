import java.util.Stack;

public class RemoveDuplicateLetters {

	public String removeDuplicateLetters(String s) {
		int[] charCount = new int[26];
		boolean added[] = new boolean[26];

		for (char c : s.toCharArray()) {
			++charCount[c - 'a'];
		}

		Stack<Character> stack = new Stack<>();
		for (char c : s.toCharArray()) {
			--charCount[c - 'a'];
			if (!added[c - 'a']) {
				while (!stack.isEmpty()) {
					if (stack.peek() > c && charCount[stack.peek() - 'a'] > 0) {
						added[stack.pop() - 'a'] = false;
					} else {
						break;
					}
				}

				stack.add(c);
				added[c - 'a'] = true;
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		return sb.reverse().toString();
	}
}
