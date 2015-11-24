package SRM673;

import java.util.HashMap;
import java.util.Map;

/*Approach 1
//1
Hash the elements and then count those occuring once
Time : 2 * O(notes) 
Memory : O(notes)
*/
public class BearSong {
	
	public int countRareNotes(int[] notes) {
	   int finalCount = 0;
	   
	   Map<Integer, Short> countMap = new HashMap<Integer, Short>();
	   for (int i : notes) {
		   Short count = countMap.get(i);
		   if (count == null) {
			   count = 0;
		   }
		   count++;
		   countMap.put(i, count);
	   }
	   
	   for (Short value : countMap.values()) {
		   if (value == 1) {
			   finalCount++;
		   }
	   }
	   return finalCount;
	}
	
	public static void main (String args[]) {
		BearSong bs = new BearSong();
		System.out.println (bs.countRareNotes(new int []{9,10,7,8,9}));
		System.out.println (bs.countRareNotes(new int []{8,8,7,6,7,3,5,10,9,3}));
		System.out.println (bs.countRareNotes(new int []{234,462,715,596,906}));
		System.out.println (bs.countRareNotes(new int []{17}));
		System.out.println (bs.countRareNotes(new int []{1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,
				1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,
				1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000}));
	}
}
