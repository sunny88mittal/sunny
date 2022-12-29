import java.util.Stack;

public class DailyTempratures {
	public int[] dailyTemperatures(int[] temperatures) {
		int length = temperatures.length;
		int[] result = new int[length];
		Stack<Integer> stack = new Stack<>();
		for (int i = length - 1; i >= 0; i--) {
			while (!stack.isEmpty()) {
				if (temperatures[stack.peek()] > temperatures[i]) {
					result[i] = stack.peek() - i;
					stack.add(i);
					break;
				} else {
					stack.pop();
				}
			}

			if (stack.isEmpty()) {
				result[i] = 0;
				stack.push(i);
			}
		}
		return result;
	}
}
