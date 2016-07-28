package chapter2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ReusingLambdaExpressions {

	public static void main(String args[]) {
		final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
		final List<String> comrades = Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");
		final List<String> editors = Arrays.asList("Brian", "Jackie", "John", "Mike");

		//Lot of code duplication
		final long countFriendsStartN = friends.stream().filter(name -> name.startsWith("N")).count();
		final long countComradesStartN = comrades.stream().filter(name -> name.startsWith("N")).count();
		final long countEditorsStartN = editors.stream().filter(name -> name.startsWith("N")).count();
		
		//Using a predicate
		final Predicate<String> startsWitN = name -> name.startsWith("N");
		final long countFriendsStartNP = friends.stream().filter(startsWitN).count();
		final long countComradesStartNP = comrades.stream().filter(startsWitN).count();
		final long countEditorsStartNP = editors.stream().filter(startsWitN).count();
	}
}
