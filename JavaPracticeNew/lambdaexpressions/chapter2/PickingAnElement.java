package chapter2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PickingAnElement {

	public static void main(String args[]) {
		final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
		pickName(friends, "N");
	}

	public static void pickName(final List<String> names, final String startingLetter) {
		final Optional<String> foundName = 
				names.stream().filter(name -> name.startsWith(startingLetter)).findFirst();
		System.out.println(
				String.format("A name starting with %s: %s", 
						startingLetter, foundName.orElse("No name found")));
	}
}
