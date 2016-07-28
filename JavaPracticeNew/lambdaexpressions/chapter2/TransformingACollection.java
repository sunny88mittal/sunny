package chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransformingACollection {

	//Convert the names to upercase
	public static void main(String args[]) {
		final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
		
		//Using for each and a new list to store output
		final List<String> uppercaseNames = new ArrayList<String>();
		friends.forEach(name -> uppercaseNames.add(name.toUpperCase()));
		System.out.println(uppercaseNames);
		
		//Using Streams
		friends.stream()
		       .map(name -> name.toUpperCase())
		       .forEach(name -> System.out.print(name + " "));
		System.out.println();
		
		//Using method reference
		friends.stream()
		       .map(String::toUpperCase) //changed in this line
		       .forEach(name -> System.out.print(name + " "));
	}
}
