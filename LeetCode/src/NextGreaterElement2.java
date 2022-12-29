import java.util.Stack;

public class NextGreaterElement2 {
	public int[] nextGreaterElements(int[] nums) {
		int length = nums.length;
		Stack<Integer> stack = new Stack<>();
		int[] result = new int[length];

		for (int j = 0; j < 2; j++) {
			for (int i = length - 1; i >= 0; i--) {
				while (!stack.isEmpty()) {
					if (nums[stack.peek()] > nums[i]) {
						result[i] = nums[stack.peek()];
						stack.push(i);
						break;
					} else {
						stack.pop();
					}
				}

				if (stack.isEmpty()) {
					result[i] = -1;
					stack.push(i);
				}
			}
		}

		return result;
	}
}
