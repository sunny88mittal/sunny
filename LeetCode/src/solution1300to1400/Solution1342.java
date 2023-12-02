package solution1300to1400;

public class Solution1342 {
	public int numberOfSteps(int num) {
		int steps = 0;
		while (num != 0) {
			if (num % 2 == 0) {
				num /= 2;
			} else {
				num -= 1;
			}
			++steps;
		}
		return steps;
	}
}
