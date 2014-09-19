package SRM622;

/**
 * 1. Convert all the numbers to the min box size required
 * 2. Sort the boxes according to their size
 * 3. Merge the boxes
 *    a). If only 1 box left break
 *    b). Take two boxes and combine them
 *    c). Place the new box at correct position to keep the boxes sorted 
 *    d). Go to a
 * 4. Return the box size
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BoxesDiv2 {
	
	public int findSize(int[] candyCounts) {
	    for (int i=0; i<candyCounts.length; i++) {
	      candyCounts[i] = 	getNearestPowerTo2(candyCounts[i]);
	    }
	    Arrays.sort(candyCounts);
		return getMinVolume(candyCounts);
	}
	
	private int getNearestPowerTo2(int n) {
		int power = 1;
		while (power <n) {
			power = power * 2;
		}
		return power;
	}
	

	private int getMinVolume(int[] sortedBoxVolumes) {
		List<Integer> sortedBoxVolList = new LinkedList<Integer>();
		for (int i=0; i<sortedBoxVolumes.length; i++) {
			sortedBoxVolList.add(sortedBoxVolumes[i]);
		}
		
		while (sortedBoxVolList.size() > 1) {
			int b1 = sortedBoxVolList.remove(0);
			int b2 = sortedBoxVolList.remove(0);
			int max = Math.max(b1, b2) * 2;
			int i=0;
			while (i < sortedBoxVolList.size() && max > sortedBoxVolList.get(i)) {
				i++;
			}
			sortedBoxVolList.add(i, max);
		}
		return sortedBoxVolList.get(0);
	}
}
