package SRM622;

import org.testng.annotations.Test;

public class SubsetsTest {
	@Test
	public void SubsetsTestTest() {
		Subsets subsets = new Subsets();
		assert subsets.findSubset(new int[] {1,1,1}) == 2;
		subsets = new Subsets();
		assert subsets.findSubset(new int[] {1,1,1,1,2,2,2,2}) == 13;
		subsets = new Subsets();
		assert subsets.findSubset(new int[] {1,2,3,4}) == 3;
		subsets = new Subsets();
		assert subsets.findSubset(new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,10,20,30,40,50}) == 77;
		subsets = new Subsets();
		assert subsets.findSubset(new int[] {1,1,1,1,1,1,1,1,1,1,1,2,3,4,2,2,2}) == 100;
		subsets = new Subsets();
		assert subsets.findSubset(new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
				 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
				 1,1,1,1,1,1,2,2,2,3,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22}) == 8050;
		subsets = new Subsets();
		assert subsets.findSubset(new int[]	{5,3}) == 0;
	}
}
