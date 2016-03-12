package StringMatching;

import java.util.ArrayList;
import java.util.List;

public class ZAlgorithm {

	public static void main(String args[]) {
		String text = "aabxaabxcaabxaabxay";
		String pattern = "aabx";
		List<Integer> patternLocations = findPattern(text.toCharArray(), pattern.toCharArray());
		for (Integer location : patternLocations) {
			System.out.println(location);
		}
	}

	private static List<Integer> findPattern(char[] text, char[] pattern) {
		// Create a combined String as pattern$text
		char[] newString = new char[pattern.length + text.length + 1];
		int i = 0;
		for (char ch : pattern) {
			newString[i] = ch;
			i++;
		}
		newString[i] = '$';
		i++;
		for (char ch : text) {
			newString[i] = ch;
			i++;
		}

		// Create the Z String
		int[] zresult = computeZValues(newString);

		// Find the macthes
		List<Integer> result = new ArrayList<Integer>();
		for (int k = 0; k < zresult.length; k++) {
			if (zresult[k] == pattern.length) {
				result.add(k - pattern.length - 1);
			}
		}
		return result;
	}

	private static int[] computeZValues(char[] string) {
		int length = string.length;
		int[] zValues = new int[length];
		int left = 1;
		int right = 1;
		for (int k = 1; k < length; k++) {
			if (k < right) {
				int zk = zValues[k - left];
				if (k + zk < right) {
					zValues[k] = zk;
				} else {
					left = k;
					while (right < length && string[right - left] == string[right]) {
						right++;
					}
					zValues[k] = right - left;
				}
			} else {
				left = right = k;
				while (right < length && string[right - left] == string[right]) {
					right++;
				}
				zValues[k] = right - left;
			}
		}
		return zValues;
	}
}
