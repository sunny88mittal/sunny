package solution600to700;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution658 {
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		int l = 0;
		int r = arr.length - k;

		while (l < r) {
			final int m = (l + r) / 2;
			if (x - arr[m] <= arr[m + k] - x)
				r = m;
			else
				l = m + 1;
		}

		return Arrays.stream(arr, l, l + k).boxed().collect(Collectors.toList());
	}

	public static void main(String args[]) {
		Solution658 obj = new Solution658();
		int nos[] = new int[] { 0, 0, 1, 2, 3, 3, 4, 7, 7, 8 };
		obj.findClosestElements(nos, 3, 5);
	}
}
