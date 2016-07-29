package chapter2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JoiningElements {

	public static void main(String args[]) {
		final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
		System.out.println(String.join(", ", friends));
		
		System.out.println(friends.stream().map(String :: toUpperCase).collect(Collectors.joining(", ")));
	}
}
