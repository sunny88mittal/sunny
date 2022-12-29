
public class ConcatenationOfArray {

	public int[] getConcatenation(int[] nums) {
		int length = nums.length;
		int[] resultArray = new int[length * 2];
		for (int i = 0; i < length; i++) {
			resultArray[i] = nums[i];
			resultArray[i + length] = nums[i];
		}
		return resultArray;
	}
}
