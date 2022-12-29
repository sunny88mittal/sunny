
public class MedianSortedArrays {

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;
		int mal = m + n;
		int mergedArray[] = new int[mal];

		int i = 0, j = 0, k = 0;
		while (i < m && j < n) {
			if (nums1[i] < nums2[j]) {
				mergedArray[k] = nums1[i];
				++i;
			} else {
				mergedArray[k] = nums2[j];
				++j;
			}
			++k;
		}

		while (i < m) {
			mergedArray[k] = nums1[i];
			++i;
			++k;
		}

		while (j < n) {
			mergedArray[k] = nums2[j];
			++j;
			++k;
		}

		double median = mergedArray[mal / 2];
		if (mal % 2 != 1) {
			median = (median + mergedArray[mal / 2 - 1]) / 2;
		}

		return median;
	}

	public static void main(String args[]) {
		MedianSortedArrays obj = new MedianSortedArrays();
		int m[] = new int[] { 1, 2 };
		int n[] = new int[] { 3, 4 };
		obj.findMedianSortedArrays(m, n);
	}
}
