package solution2000to2100;

public class Solution2024 {
	public int maxConsecutiveAnswers(String answerKey, int k) {
		int t = 0;
		int f = 0;
		int max = k;
		int n = answerKey.length();
		int start = 0;

		for (int i = 0; i < n; i++) {
			if (answerKey.charAt(i) == 'T') {
				++t;
			} else {
				++f;
			}

			int min = Math.min(t, f);
			if (min > k) {
				int length = i - start;
				if (length > max) {
					max = length;
				}

				if (answerKey.charAt(start) == 'T') {
					--t;
				} else {
					--f;
				}
				++start;
			}
		}

		if (n - start > max) {
			max = n - start;
		}

		return max;
	}

	public static void main(String args[]) {
		Solution2024 obj = new Solution2024();
		obj.maxConsecutiveAnswers("TTFTTFTT", 1);
	}
}
