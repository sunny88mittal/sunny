package chapter2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class LexicalScopingAndClosure {

	public static void main(String args[]) {
		final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

		// Using hugher order function
		final long countFriendsStartN = friends.stream().filter(checkIfStartsWith("N")).count();
		final long countFriendsStartB = friends.stream().filter(checkIfStartsWith("B")).count();

		System.out.println("With N :" + countFriendsStartN);
		System.out.println("With B :" + countFriendsStartB);

		// To narrow down the scope to the function rather than class as we did
		// with the static method we can use a function instance
		final Function<String, Predicate<String>> startsWithLetter = (String letter) -> {
			Predicate<String> checkStartsWith = (String name) -> name.startsWith(letter);
			return checkStartsWith;
		};

		//Return a lambda expression rather than explicitly creating a predicate
		final Function<String, Predicate<String>> startsWithLetter1 = 
				(String letter) -> (String name) -> name.startsWith(letter);
		
		//Remove types also		
		final Function<String, Predicate<String>> startsWithLetter2 =
						letter -> name -> name.startsWith(letter);
		
		final long countFriendsStartNx = friends.stream().filter(startsWithLetter.apply("N")).count();
		final long countFriendsStartBx= friends.stream().filter(startsWithLetter.apply("B")).count();
	}

	public static Predicate<String> checkIfStartsWith(final String letter) {
		return name -> name.startsWith(letter);
	}
}
