package chapter2;

import java.util.Arrays;
import java.util.List;

public class ReudcingColelctionToSingleValue {

	// Find total no of characters in the list
	public static void main(String args[]) {
		final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
		final int count = friends.stream().mapToInt(name -> name.length()).sum();
		System.out.println(count);
	}
}
