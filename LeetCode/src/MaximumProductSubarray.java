
public class MaximumProductSubarray {
	public int maxProduct(int[] nums) {
		int length = nums.length;
		int maxPosProd[] = new int[length];
		int maxNegProd[] = new int[length];
		int maximum = nums[0];
		maxNegProd[0] = nums[0];
		maxPosProd[0] = nums[0];

		for (int i = 1; i < length; i++) {
			int prodWithNeg = nums[i] * maxPosProd[i - 1];
			int prodWithPos = nums[i] * maxNegProd[i - 1];

			maxPosProd[i] = nums[i];
			if (prodWithNeg > maxPosProd[i]) {
				maxPosProd[i] = prodWithNeg;
			}
			if (prodWithPos > maxPosProd[i]) {
				maxPosProd[i] = prodWithPos;
			}

			maxNegProd[i] = nums[i];
			if (prodWithNeg < maxNegProd[i]) {
				maxNegProd[i] = prodWithNeg;
			}
			if (prodWithPos < maxNegProd[i]) {
				maxNegProd[i] = prodWithPos;
			}

			if (maximum < maxPosProd[i]) {
				maximum = maxPosProd[i];
			}
		}

		return maximum;
	}

	public static void main(String args[]) {
		MaximumProductSubarray obj = new MaximumProductSubarray();
		int arr[] = new int[] { 2, 3, -1, 1, -3, 3, 0 };
		obj.maxProduct(arr);
	}
}
