package nasdaqomxsse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class KingsRace {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int testCases = Integer.parseInt(line);
		for (int i = 0; i < testCases; i++) {
			String tokens[] = br.readLine().split(" ");
			int noOfPrinces = Integer.parseInt(tokens[0]);
			int noOfHurdles = Integer.parseInt(tokens[1]);

			int[] princesStrength = new int[noOfPrinces];
			int[] hurdlesHeight = new int[noOfHurdles];

			tokens = br.readLine().split(" ");
			for (int j = 0; j < noOfPrinces; j++) {
				princesStrength[j] = Integer.parseInt(tokens[j]);
			}

			tokens = br.readLine().split(" ");
			for (int j = 0; j < noOfHurdles; j++) {
				hurdlesHeight[j] = Integer.parseInt(tokens[j]);
			}

			Arrays.sort(hurdlesHeight);

			int maxHurdlesCrossed = -1;
			int princeIndex = -1;

			for (int j = 0; j < noOfPrinces; j++) {
				int princeStrength = princesStrength[j];
				int k = modifiedBinarySearch(hurdlesHeight, princeStrength);
				
				if (k > maxHurdlesCrossed) {
					princeIndex = j;
					maxHurdlesCrossed = k;
				}

				
				if (maxHurdlesCrossed == noOfHurdles) {
					break;
				}
			}

			System.out.println(princeIndex);
		}
	}
	
	private static int modifiedBinarySearch (int[] hurdlesHeight, int strength) {
		int left = 0;
		int right = hurdlesHeight.length-1;
		int middle = (left + right)/2;
		while (right > left) {
			if (strength == hurdlesHeight[middle]) {
				return middle +1;
			} else if (strength > hurdlesHeight[middle]) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
			middle = (left + right)/2;
		}
		
		if (left == right) {
			if (strength >= hurdlesHeight[left]) {
				return left + 1;
			}
		}
		
		return left;
	}
}
