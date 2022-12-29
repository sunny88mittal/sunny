import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ThreeSum2 {

	private static int[] findThreeSum(int[] nums) {
		int length = nums.length;
		Arrays.sort(nums);
		for (int i = 0; i < length; i++) {
			int element = nums[i];
			int[] elementsWithRemainingsum = getElementsWithSum(nums, i + 1);
			if (elementsWithRemainingsum.length != 0) {
				int[] result = new int[] { element, elementsWithRemainingsum[0], elementsWithRemainingsum[1] };
			}
		}

		return null;
	}

	private static int[] getElementsWithSum(int[] nums, int startIndex) {
		
		return new int[] {};
	}
}
