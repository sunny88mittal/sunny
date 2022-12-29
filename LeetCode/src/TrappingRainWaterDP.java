
public class TrappingRainWaterDP {

	public int trap(int[] height) {
		int result = 0;
		int length = height.length;
		int leftMax[] = new int[length];
		int rightMax[] = new int[length];

		for (int i = 1; i < length; i++) {
			if (leftMax[i - 1] < height[i - 1]) {
				leftMax[i] = height[i - 1];
			} else {
				leftMax[i] = leftMax[i - 1];
			}
		}

		for (int i = length - 2; i >= 0; i--) {
			if (rightMax[i + 1] < height[i + 1]) {
				rightMax[i] = height[i + 1];
			} else {
				rightMax[i] = rightMax[i + 1];
			}
		}

		for (int i = 0; i < length; i++) {
			int resulti = Math.min(leftMax[i], rightMax[i]) - height[i];
			if (resulti > 0) {
				result += resulti;
			}
		}

		return result;
	}
}
