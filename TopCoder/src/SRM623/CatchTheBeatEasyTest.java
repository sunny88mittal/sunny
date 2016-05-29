package SRM623;

import org.testng.annotations.Test;

public class CatchTheBeatEasyTest {
	@Test
	public void catchTheBeatEasyTest() {
		CatchTheBeatEasy catchTheBeatEasy = new CatchTheBeatEasy();
		
		assert catchTheBeatEasy.ableToCatchAll(new int[] {-1, 1, 0}, new int[] {1, 3, 4}).equals(CatchTheBeatEasy.ABLE_TO_CATCH);
		assert catchTheBeatEasy.ableToCatchAll(new int[] {-3}, new int[] {2}).equals(CatchTheBeatEasy.NOT_ABLE_TO_CATCH);
		assert catchTheBeatEasy.ableToCatchAll(new int[] {-1, 1, 0}, new int[] {1, 2, 4}).equals(CatchTheBeatEasy.NOT_ABLE_TO_CATCH);
		assert catchTheBeatEasy.ableToCatchAll(new int[] {0, -1, 1}, new int[] {9, 1, 3}).equals(CatchTheBeatEasy.ABLE_TO_CATCH);
		assert catchTheBeatEasy.ableToCatchAll(new int[] {70,-108,52,-70,84,-29,66,-33}, new int[] {141,299,402,280,28,363,427,232}).equals(CatchTheBeatEasy.NOT_ABLE_TO_CATCH);
		assert catchTheBeatEasy.ableToCatchAll(new int[] {-175,-28,-207,-29,-43,-183,-175,-112,-183,-31,-25,-66,-114,-116,-66}, new int[] {320,107,379,72,126,445,318,255,445,62,52,184,247,245,185}).equals(CatchTheBeatEasy.ABLE_TO_CATCH);
		assert catchTheBeatEasy.ableToCatchAll(new int[] {0,0,0,0}, new int[] {0,0,0,0}).equals(CatchTheBeatEasy.ABLE_TO_CATCH);
	}
}
