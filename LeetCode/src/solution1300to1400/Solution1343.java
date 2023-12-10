package solution1300to1400;

public class Solution1343 {
	public int numOfSubarrays(int[] arr, int k, int threshold) {
		int n = arr.length;
		int sum = 0;
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (i >= k) {
				float average = (float) sum / (float) k;
				if (average >= threshold) {
					++count;
				}
				sum -= arr[i - k];
			}
			sum += arr[i];
		}

		if ((float) sum / (float) k >= threshold) {
			++count;
		}

		return count;
	}
}
