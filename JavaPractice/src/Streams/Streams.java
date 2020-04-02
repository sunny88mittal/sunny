package Streams;

import java.util.Arrays;
import java.util.stream.Stream;

public class Streams {

	public static void main(String args[]) {
		String[] arr = new String[] { "a", "a", "b", "c" };

		// Stream Creation
		Stream<String> stream = Arrays.stream(arr);

		// Operations Example
		int count = (int) stream.distinct().count();
		System.out.println("Count is :" + count);

		// Iterating
		boolean isExist = Arrays.stream(arr).anyMatch(element -> element.contains("a"));
		System.out.println("a exists :" + isExist);

		// Filtering
		Stream<String> filteredList = Arrays.stream(arr).filter(element -> element.contains("a"));
		System.out.println("Filtered List :" + filteredList);
	}
}
