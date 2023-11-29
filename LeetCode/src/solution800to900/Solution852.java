package solution800to900;

public class Solution852 {
	public int peakIndexInMountainArray(int[] arr) {
		int l = 0;
		int r = arr.length;
		int m = (l + r) / 2;

		while (l < r) {
			// Mountain
			if (arr[m] > arr[m - 1] && arr[m] > arr[m + 1]) {
				break;
			}

			// Decreasing
			if (arr[m - 1] > arr[m] && arr[m] > arr[m + 1]) {
				r = m;
			}

			// Increasing
			if (arr[m - 1] < arr[m] && arr[m] < arr[m + 1]) {
				l = m;
			}

			m = (l + r) / 2;
		}

		return m;
	}

	public static void main(String args[]) {
		Solution852 obj = new Solution852();
		int[] nos = new int[] { 0,10,5,2 };
		obj.peakIndexInMountainArray(nos);
	}
}
