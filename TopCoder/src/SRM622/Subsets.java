package SRM622;


/**
 * 1. Get the unique numbers in a list and keep the count of unique numbers in a map
 * 2. Sort the unique numbers in ascending order
 * 3. Get the ones count
 * 4. If ones count  == 0 return 0;
 * 5. If ones count == arrayLength return arrayLength -1;
 * 6. For each number starting from index 1 form all the subsets which satisfy the condition that sum > product
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subsets {
	
	private List<Integer> uniqueNumbers = new ArrayList<Integer>();
	private Map<Integer, Integer> numbersCount = new HashMap<Integer, Integer>();
	private int onescount = 0;
	
	public int findSubset(int[] numbers) {
		int subsets = 0;
		for (int i=0; i<numbers.length; i++) {
			Integer key = numbers[i];
			Integer exisitingCount = numbersCount.get(key);
			if (exisitingCount == null) {
				numbersCount.put(key, 1);
				uniqueNumbers.add(key);
			} else {
				numbersCount.put(key, exisitingCount + 1);
			}
		}
		
		Collections.sort(uniqueNumbers);
		
		if (uniqueNumbers.get(0) != 1) {
			return 0; //No ones exist return 0
		} else {
			onescount = numbersCount.get(uniqueNumbers.get(0)); //Keep the ones count
		}
		
		if (onescount == numbers.length) { //All 1's simply it is n-1
			return numbers.length - 1;
		}
		
		subsets = onescount - 1; //Subsets for 1's
		
		//For each number form all the possible combinations while satisfying the conditions
		for (int i=1; i<uniqueNumbers.size(); i++) {
			Integer number = uniqueNumbers.get(i);
			subsets = subsets + getSubSetCount(i, number + onescount, number, true); //pass the flag as true since we have already used the number once here
		}
		return subsets;
	}
	
	private int getSubSetCount (int index, int sum, int product, boolean decrementCounter) {
		int subsetcount = 0;
		if (index < uniqueNumbers.size()) {
			int number = uniqueNumbers.get(index);
			int count = numbersCount.get(number);
			int counter = 0;
			if (decrementCounter) count = count - 1;
			while (counter <= count ) {
				int tempsum = sum + number * counter;
				int tempproduct = (int) (product * Math.pow(number, counter));
				if (tempsum > tempproduct) {
					subsetcount += getSubSetCount(index + 1, tempsum, tempproduct, false);
					counter++;	
				} else {
					break;
				}
			}
		} else if (sum > product) {
			subsetcount = sum - product;
		}
		return subsetcount;
	}
}
