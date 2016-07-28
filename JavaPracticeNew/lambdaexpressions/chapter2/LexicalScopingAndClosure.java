package chapter2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LexicalScopingAndClosure {

	public static void main(String args[]) {
		final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

		final long countFriendsStartN = friends.stream().filter(checkIfStartsWith("N")).count();
		final long countFriendsStartB = friends.stream().filter(checkIfStartsWith("B")).count();

		System.out.println("With N :" +  countFriendsStartN);
		System.out.println("With B :" +  countFriendsStartB);
	}

	public static Predicate<String> checkIfStartsWith(final String letter) {
		return name -> name.startsWith(letter);
	}
}
