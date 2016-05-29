package SRM621;

import org.testng.annotations.Test;

public class MixingColorsTest {
	@Test
	public void f() {
		MixingColors mixingColors = new MixingColors();
		assert mixingColors.minColors(new int[] {1,7,3}) == 3;
		assert mixingColors.minColors(new int[] {10}) == 1;
		assert mixingColors.minColors(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}) == 4;
		assert mixingColors.minColors(new int[] {534, 251, 76, 468, 909, 410, 264, 387, 102, 982, 199, 111, 659, 386, 151}) == 10;
		assert mixingColors.minColors(new int[] {4, 8, 16, 32, 64, 128, 256, 512, 1024}) == 9;
	}
}
