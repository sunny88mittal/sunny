package SRM621;

import org.testng.annotations.Test;

public class TwoWaysSortingTest {
	@Test
	public void twoWaysSortingTest() {
		TwoWaysSorting twoWaysSorting =  new TwoWaysSorting();
		
		assert twoWaysSorting.sortingMethod(new String[] {"a", "aa", "bbb"}).equals(twoWaysSorting.both);
		assert twoWaysSorting.sortingMethod(new String[] {"c", "bb", "aaa"}).equals(twoWaysSorting.lengths);
		assert twoWaysSorting.sortingMethod(new String[] {"etdfgfh", "aio"}).equals(twoWaysSorting.none);
		assert twoWaysSorting.sortingMethod(new String[] {"aaa", "z"}).equals(twoWaysSorting.lexicographically);
		assert twoWaysSorting.sortingMethod(new String[] {"z"}).equals(twoWaysSorting.both);
		assert twoWaysSorting.sortingMethod(new String[] {"abcdef", "bcdef", "cdef", "def", "ef", "f", "topcoder"}).equals(twoWaysSorting.lexicographically);
	}
}
